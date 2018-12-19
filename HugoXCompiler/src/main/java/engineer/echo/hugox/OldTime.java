package engineer.echo.hugox;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
/**
 *  OldTime.java
 *  Info: OldTime.java
 *  Created by Plucky(plucky@echo.engineer) on 2018/12/19 - 7:14 PM
 *  more about me: http://www.1991th.com
 */

public class OldTime {
    /**
     * 最原始的方式生成文件
     *
     * @param filer Filer
     * @return boolean
     */
    public static boolean testCreateFile(Filer filer) {
        try {
            JavaFileObject f = filer.createSourceFile("engineer.echo.HelloWorld");
            Writer w = f.openWriter();
            try {
                PrintWriter pw = new PrintWriter(w);
                pw.println("//This codes are generated automatically. Do not modify!");
                pw.println("package engineer.echo;");
                pw.println("public class HelloWorld{");
                pw.println(" public void print() {");
                pw.println("  System.out.println(\"Hello Engineer!\");");
                pw.println(" }");
                pw.println("}");
                pw.flush();
            } finally {
                w.close();
            }
            return true;
        } catch (IOException x) {
            return false;
        }
    }
}
