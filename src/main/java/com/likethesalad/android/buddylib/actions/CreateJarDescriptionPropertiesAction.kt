package com.likethesalad.android.buddylib.actions

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.likethesalad.android.buddylib.actions.base.BaseAction
import com.likethesalad.android.common.utils.DirectoryCleaner
import java.io.File
import java.util.Properties

@AutoFactory
class CreateJarDescriptionPropertiesAction(
    @Provided private val directoryCleaner: DirectoryCleaner,
    private val pluginNames: Set<String>,
    private val outputDir: File
) : BaseAction {
    companion object {
        private const val PLUGINS_PROPERTIES_FILE_NAME = "plugins.properties"
        private const val PLUGINS_PROPERTIES_CLASSES_KEY = "plugin-classes"
    }

    override fun execute() {
        cleanUpDir()
        val propertiesFile = File(outputDir, PLUGINS_PROPERTIES_FILE_NAME)
        val properties = Properties()
        properties.setProperty(
            PLUGINS_PROPERTIES_CLASSES_KEY,
            pluginNames.joinToString(",")
        )
        propertiesFile.writer().use {
            properties.store(it, null)
        }
    }

    private fun cleanUpDir() {
        directoryCleaner.cleanDirectory(outputDir)
    }
}