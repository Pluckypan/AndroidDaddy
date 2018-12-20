package echo
import org.gradle.api.Plugin
import org.gradle.api.Project

class DateAndTimePlugin implements Plugin<Project> {

    void apply(Project project) {

        project.extensions.create("dateAndTime", DateAndTimePluginExtension)

        project.task('showCurrentTime') << {
            println "@echo->Current time is " + new Date().format(project.dateAndTime.timeFormat)
        }

        project.tasks.create('showCurrentDate') << {
            println "@echo->Current date is " + new Date().format(project.dateAndTime.dateFormat)
        }
    }
}