package job.runner

import com.sun.xml.internal.ws.util.StringUtils

class ArgumentInjectorService {

    public static final String ARGS_MAP = "argsMap"
    static transactional = false

    def injectArguments(job, Map arguments) {
        injectProperty(job, ARGS_MAP, arguments)

        arguments.keySet().each {
            injectProperty(job, it as String, arguments)
        }

        return this
    }

    private static void injectProperty(job, String propertyName, Map arguments) {
        if (job.properties.containsKey(propertyName)) {
            try {
                if (propertyName == ARGS_MAP) {
                    job."$propertyName" = arguments
                } else {
                    job."$propertyName" = arguments[propertyName]
                }
            } catch (GroovyCastException) {
                if (propertyName != ARGS_MAP) {
                    def method = job.getClass().getDeclaredMethod("get${StringUtils.capitalize(propertyName)}")
                    def type = method.getReturnType()
                    try {
                        job."$propertyName" = arguments[propertyName].asType(type)
                    } catch (Exception) {
                        //do nothing, not compatible
                    }
                }
            }
        }
    }
}
