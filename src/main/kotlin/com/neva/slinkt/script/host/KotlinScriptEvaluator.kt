package com.neva.slinkt.script.host

import org.osgi.framework.FrameworkUtil
import org.osgi.framework.wiring.BundleWiring
import org.osgi.service.component.annotations.Component
import org.slf4j.LoggerFactory
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassloader
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate
import kotlin.script.experimental.jvmhost.createJvmEvaluationConfigurationFromTemplate

@Component(immediate = true, service = [KotlinScriptEvaluator::class])
class KotlinScriptEvaluator {

  fun eval(
    sourceCode: SourceCode,
    compilationOptions: ScriptCompilationConfiguration.Builder.() -> Unit = {},
    evaluationOptions: ScriptEvaluationConfiguration.Builder.() -> Unit = {}
  ): ResultWithDiagnostics<EvaluationResult> {
    val host = BasicJvmScriptingHost().apply {}

    val bundle = FrameworkUtil.getBundle(javaClass)
    val bundleWiring = bundle.adapt(BundleWiring::class.java)
    val classLoader = bundleWiring.classLoader

    val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<Any> {
        jvm {
            dependenciesFromClassloader(
                    wholeClasspath = true,
                    classLoader = classLoader
            )
            compilerOptions.append("-Xintellij-plugin-root=${System.getProperty("user.dir")}")
        }
      compilationOptions()
    }
    val evaluationConfiguration = createJvmEvaluationConfigurationFromTemplate<Any> {
        jvm {
            // mainArguments()
        }
        evaluationOptions()
    }
    return host.eval(sourceCode, compilationConfiguration, evaluationConfiguration)
  }

  companion object {
    val LOG = LoggerFactory.getLogger(KotlinScriptEvaluator::class.java)
  }
}
