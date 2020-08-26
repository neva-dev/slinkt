package com.neva.slinkt

import org.osgi.framework.FrameworkUtil
import org.osgi.framework.wiring.BundleWiring
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.slf4j.LoggerFactory
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvm.dependenciesFromClassloader
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate
import kotlin.script.experimental.jvmhost.createJvmEvaluationConfigurationFromTemplate

@Component(immediate = true, service = [KotlinScriptEvaluator::class])
class KotlinScriptEvaluator {

  @Activate
  fun activate() {
    val host = BasicJvmScriptingHost().apply {

    }
    val scriptSource = StringScriptSource("""
      println("Hello World!")
    """.trimIndent())

    val bundle = FrameworkUtil.getBundle(javaClass)
    val bundleWiring = bundle.adapt(BundleWiring::class.java)
    val classLoader = bundleWiring.classLoader

    val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<Any> {
      jvm {
        dependenciesFromClassloader(
          wholeClasspath = true,
          classLoader = classLoader
        )
      }
    }
    val evaluationConfiguration = createJvmEvaluationConfigurationFromTemplate<Any> {}
    val result = host.eval(scriptSource, compilationConfiguration, evaluationConfiguration)

    LOG.error("Kotlin script result:\n{}", result)
  }

  companion object {
    val LOG = LoggerFactory.getLogger(KotlinScriptEvaluator::class.java)
  }
}
