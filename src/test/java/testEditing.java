import org.apache.poi.hwpf.extractor.WordExtractor;
import org.junit.Test;

import java.io.*;

public class testEditing {

    @Test
    public void testReadTXT(){
        //逐行读
        BufferedReader br = null;
        try {
            Reader reader = new FileReader("D:\\javaDemo\\fileupload\\src\\main\\webapp\\uploadFile\\test.txt");
            br = new BufferedReader(reader);
            String line;
            while(null != (line = br.readLine())){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*@Test
    public void readWord() {
        String filePath = "D:\\IdeaDemo\\landian\\AMS\\src\\main\\webapp\\uploadFile\\ironman\\多媒体.docx";
        String buffer = "";
        try {
            if (filePath.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(filePath));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (filePath.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(buffer);
    }*/

}
