package com.neva.slinkt.script.engine

import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineFactoryBase
import org.jetbrains.kotlin.cli.common.repl.ScriptArgsWithTypes
import org.jetbrains.kotlin.script.jsr223.KotlinStandardJsr223ScriptTemplate
import org.osgi.framework.FrameworkUtil
import org.osgi.framework.wiring.BundleWiring
import java.io.File
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngine

class KotlinEngineFactory : KotlinJsr223JvmScriptEngineFactoryBase() {

  override fun getScriptEngine(): ScriptEngine {
    System.setProperty("project.structure.add.tools.jar.to.new.jdk", "false")

    return KotlinEngine(
      FrameworkUtil.getBundle(javaClass).adapt(BundleWiring::class.java).classLoader,
      this,
      listOf(File("/Users/krystian.panek/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-util/1.4.0/7a46a84420efbeff02a9e2cb7306bbe288bf01cc/kotlin-script-util-1.4.0.jar")),
      KotlinStandardJsr223ScriptTemplate::class.qualifiedName!!,
      { ctx, types ->
        ScriptArgsWithTypes(arrayOf(ctx.getBindings(ScriptContext.ENGINE_SCOPE)), types ?: emptyArray())
      },
      arrayOf(Bindings::class)
    )
  }
}
