import com.landian.dao.FileDao;
import com.landian.dao.impl.FileDaoImpl;
import com.landian.domain.PageBean;
import com.landian.service.FileService;
import com.landian.service.impl.FileServiceImpl;
import com.landian.util.FileUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;

public class testFileDao {
    @Test
    public void testFileDao() throws IOException {
        String classPath = this.getClass().getResource("/").getPath().replaceFirst("/","");
        String uploadFileRoot = classPath.replaceAll("target/test-classes/","")+"src/main/webapp/uploadFile/";
        System.out.println(uploadFileRoot);
        java.io.File file = new File(uploadFileRoot+"ts.txt");
        file.createNewFile();
        System.out.println(file.getPath());
    }

    @Test
    public void testFindAllFile(){
        FileUtil fileUtil = new FileUtil();
        java.io.File[] files = fileUtil.selectAllFile("D:/IdeaDemo/landian/AMS/src/main/webapp/uploadFile/ironman/");
        if (files != null) {
            for (java.io.File fileChildDir : files) {
                //输出文件名或者文件夹名
                System.out.print(fileChildDir.getName());
                if (fileChildDir.isDirectory()) {
                    System.out.println(" :  此为目录名");
                    //通过递归的方式,可以把目录中的所有文件全部遍历出来
                    //FileUtil(fileChildDir.getAbsolutePath());
                }
                if (fileChildDir.isFile()) {
                    System.out.println(" :  此为文件名");
                }
            }
        }
    }

    @Test
    public void findAllFile(){
        FileService service = new FileServiceImpl();
        PageBean<com.landian.domain.File> pb = service.findAllFileByPage("1","8");
        List<com.landian.domain.File> list = pb.getList();
        Iterator<com.landian.domain.File> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void deleteFile(){
        File file = new File("D:/IdeaDemo/landian/AMS/src/main/webapp/uploadFile/ironman/计科20Web2课程期末考核作业.docx");
        file.delete();
    }
    @Test
    public void findPathById(){
        FileDao fileDao = new FileDaoImpl();
        System.out.println(fileDao.findFileById("5"));
    }
    @Test
    public void downloadFile() throws IOException {
        InputStream is;
        try {
            is = new FileInputStream(new File("D:/IdeaDemo/landian/AMS/src/main/webapp/uploadFile/ironman/多媒体.docx"));
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HWPFDocument document = new HWPFDocument(fs);
            Range range = document.getRange();
            String title = null;int item=0;
            for(int i=0;i<range.numParagraphs();i++) {//通过循环读取每一段
                Paragraph para1 = range.getParagraph(i);
                CharacterRun run1=para1.getCharacterRun(0);  //获取每一段的属性
                item = i;//item用来记录当前位置
                if(run1.getFontSize()==26) {//如果字体符合，说明该段落是小标题
                    String context = "";
                    title = para1.text().trim().replaceAll("\r\n", "");//取这一段为当前的title值
                    for(int j=item+1;j<range.numParagraphs();j++) {//从当前段的下一段开始，查找所有解释段落，
                        Paragraph para2 = range.getParagraph(j);
                        CharacterRun run2=para2.getCharacterRun(0);

                        if(run2.getFontSize()==18) {//如果字体符合，说明该段落是解释段落之一

                            context = context+para2.text().trim().replaceAll("\r\n", "");//把解释段落合并成一个字符串

                        }
                        if(run2.getFontSize()==26) {//如果字体为26，说明到了下一个小标题，则退出循环
                            break;
                        }
                    }
                    //以下为插入数据库方法
                    //WordDao worddao = new WordDao();
                    //worddao.addData(title, context);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
