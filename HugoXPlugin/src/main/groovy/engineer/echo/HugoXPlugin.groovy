package engineer.echo

import org.gradle.api.Plugin
import org.gradle.api.Project

class HugoXPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create("HugoX", HugoXExtension)
        def enable = project.HugoX.enable
        println("HugoXPlugin enable=$enable")
        project.task('showHugoXInfo') << {
            println "HugoX switch on= $enable"
        }
    }
}