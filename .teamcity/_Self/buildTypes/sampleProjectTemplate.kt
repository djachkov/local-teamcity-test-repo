package _Self.buildTypes

import _Self.samplePythonStep
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BinariesParallelTemplate : Template({
    name = "Binaries Parallel Template"
    artifactRules = """
            %system.build.dir%/sampleLogs/logs/*.log=>awesomeLogs
    """.trimIndent()

    params {
        text("system.build.sample.param", "CHANGE ME", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        select("system.build.select", "SAMPLE", label = "Build Type", description = "Select sample: SAMPLE => Lorem ipsum; NOTSAMPLE => dolor sit amet, display = ParameterDisplay.HIDDEN, options = listOf("SAMPLE" to "sample", "NOTSAMPLE" to "notsample"))
    }

    vcs {
        root(AbsoluteId("SampleVCS"))
        checkoutMode = CheckoutMode.MANUAL
        cleanCheckout = true
        checkoutDir = "./build/"
    }

    steps {
        script {
            name = "Step 1"
            scriptContent = """
                echo "Build step #1"
            """.trimIndent()
        }
        step(samplePythonStep)
    }

    features {
        freeDiskSpace {
            requiredSpace = "60gb"
            failBuild = true
        }
    }

    requirements {
        equals("env.OS", "Windows_NT", "RQ_23")
        moreThan("teamcity.agent.work.dir.freeSpaceMb", "150000")
    }
})
