class JobRunnerGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/services/job/runner/tests",
    ]

    // TODO Fill in these fields
    def title = "Job Runner Plugin" // Headline display name of the plugin
    def author = "Tommy Barker"
    def authorEmail = "mingus.karate@gmail.com"
    def description = '''\
Brief summary/description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/job-runner"

}
