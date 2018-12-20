package engineer.echo

import org.gradle.api.Plugin
import org.gradle.api.Project

class HugoXPlugin implements Plugin<Project> {

    void apply(Project project) {
        //创建HugoX扩展,可以配置开关
        project.extensions.create("HugoX", HugoXExtension)
        def enable = false, ver = "xxx"
        project.afterEvaluate {
            //获取HugoX配置的参数
            def extension = project.HugoX
            enable = extension.enable
            ver = extension.version
            println("HugoXPlugin enable=$enable ver=$ver")
        }
        //创建任务
        project.task('showHugoXInfo') << {
            println "HugoX switch on= $enable ver=$ver"
        }
    }
}