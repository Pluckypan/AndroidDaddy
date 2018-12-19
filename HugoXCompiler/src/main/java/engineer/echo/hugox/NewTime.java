package engineer.echo.hugox;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;

/**
 * NewTime.java
 * Info: NewTime.java
 * Created by Plucky(plucky@echo.engineer) on 2018/12/19 - 7:17 PM
 * more about me: http://www.1991th.com
 */
public class NewTime {

    public static boolean testJavapoet(Filer filer, String name, int age) {

        //创建main方法
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();
        //创建成员变量 TAG
        FieldSpec tag = FieldSpec.builder(String.class, "TAG")
                .addModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PRIVATE)
                .initializer("$S", "Engineer")
                .build();
        //创建成员变量 name
        FieldSpec aName = FieldSpec.builder(String.class, "name")
                .addModifiers(Modifier.PRIVATE)
                .initializer("$S", name)
                .build();
        //创建成员变量 age
        FieldSpec aAge = FieldSpec.builder(int.class, "age")
                .addModifiers(Modifier.PRIVATE)
                .initializer("$L", age)
                .build();
        //创建方法 method
        MethodSpec say = MethodSpec.methodBuilder("say")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addParameter(String.class, "content")
                .addStatement("String result= $N + $S + $N + $S +$N + $S+$S",
                        "TAG", " -> I Am ", "name", ",I'm ", "age", "years old.\n", "content")
                .addStatement("System.out.println(result)")
                .addStatement("return $S", "result")
                .build();

        // 创建 HiWorld 类
        TypeSpec hiWorld = TypeSpec.classBuilder("HiWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .addMethod(say)
                .addField(tag)
                .addField(aName)
                .addField(aAge)
                .build();
        JavaFile javaFile = JavaFile.builder("engineer.echo", hiWorld)
                .addFileComment("This codes are generated automatically. Do not modify!")
                .build();
        try {
            javaFile.writeTo(filer);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }
}
