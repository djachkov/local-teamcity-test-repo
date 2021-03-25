package _Self

import _Self.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.ParameterDisplay
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import java.io.File
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot


val samplePythonStep = BuildStep {
    name = "Sample"
    type = "python"
    param("python-script-code", File("_Self/Sample.py").readText())
    param("python-exe", "%Python.3%")
}


object Project : Project({
    template(sampleProjectTemplate)
    params {
        text("system.branch_name", "%reverse.dep.*.system.branch_name%")
    }
    subProject(SubPrjct.Project)
    subProjectsOrder = arrayListOf(
            SubPrjct.Project
    )
})
