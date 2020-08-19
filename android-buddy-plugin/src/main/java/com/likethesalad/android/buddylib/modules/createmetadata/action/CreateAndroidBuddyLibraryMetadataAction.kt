package com.likethesalad.android.buddylib.modules.createmetadata.action

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.likethesalad.android.common.actions.BaseAction
import com.likethesalad.android.common.utils.Constants.LIBRARY_METADATA_DIR
import com.likethesalad.android.common.utils.Constants.PLUGINS_METADATA_FILE_NAME
import com.likethesalad.android.common.utils.Constants.PLUGINS_PROPERTIES_CLASSES_KEY
import com.likethesalad.android.common.utils.DirectoryCleaner
import com.likethesalad.android.common.utils.Logger
import java.io.File
import java.util.Properties

@AutoFactory
class CreateAndroidBuddyLibraryMetadataAction(
    @Provided private val directoryCleaner: DirectoryCleaner,
    @Provided private val logger: Logger,
    private val pluginNames: Set<String>,
    private val outputDir: File
) : BaseAction {

    override fun execute() {
        cleanUpDir()
        val metadataFile = File(
            getPropertiesDir(),
            PLUGINS_METADATA_FILE_NAME
        )
        val properties = Properties()
        properties.setProperty(
            PLUGINS_PROPERTIES_CLASSES_KEY,
            pluginNames.joinToString(",")
        )
        metadataFile.writer().use {
            properties.store(it, null)
        }

        logger.debug("Transformations found: {}", pluginNames)
    }

    private fun cleanUpDir() {
        directoryCleaner.cleanDirectory(outputDir)
    }

    private fun getPropertiesDir(): File {
        val dir = File(
            outputDir,
            LIBRARY_METADATA_DIR
        )
        dir.mkdirs()
        return dir
    }
}