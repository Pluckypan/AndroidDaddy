package engineer.echo.hugox;

import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * HugoXCompiler.java
 * Info: HugoXCompiler.java
 * Created by Plucky(plucky@echo.engineer) on 2018/12/18 - 9:36 PM
 * more about me: http://www.1991th.com
 */

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class HugoXCompiler extends AbstractProcessor {

    private Filer mFiler;
    private Messager mMessage;
    private String version = "0.0.0";
    private String name = "xxx";
    private int age = 0;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessage = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        version = processingEnvironment.getOptions().get("hugox.version");
        name = processingEnvironment.getOptions().get("hugox.name");
        String ageX = processingEnvironment.getOptions().get("hugox.age");
        try {
            age = Integer.valueOf(ageX);
        } catch (NumberFormatException e) {

        }
        printLn("init version=" + version + " name=" + name + " age=" + age, true);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(DebugLog.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                printLn("------------------------------", true);
                printLn("方法名：" + element.getSimpleName(), true);
                DebugLog ano = element.getAnnotation(DebugLog.class);
                printLn("注解信息：" + ano.showSpendTime(), true);
                printLn("------------------------------", true);
            }
        }
        OldTime.testCreateFile(mFiler);
        NewTime.testJavapoet(mFiler, name, age);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        /**
         * 指定将要扫描并处理被注解标记的类 有两种方式
         * 1. 重写 public Set<String> getSupportedAnnotationTypes()
         * 2. 在 HugoXCompiler 类头部使用注解 @SupportedAnnotationTypes("engineer.echo.hugox.DebugLog")
         */
        return Collections.singleton(DebugLog.class.getCanonicalName());
    }


    /**
     * 指明支持的源码版本(JAVA版本) 支持两种方式
     * 1. 重写 public SourceVersion getSupportedSourceVersion()
     * 2. 在 HugoXCompiler 类头部使用注解 @SupportedSourceVersion(SourceVersion.RELEASE_7)
     */
    /*
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }*/
    private void printLn(String note, boolean system) {
        if (system) {
            System.out.println(note);
        } else {
            //Note 会在控制台 打印 注: xxx
            mMessage.printMessage(Diagnostic.Kind.NOTE, note);
        }
    }
}
