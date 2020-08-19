package com.likethesalad.android.buddylib.extension

import org.gradle.api.model.ObjectFactory

open class AndroidBuddyLibExtension(objectFactory: ObjectFactory) {

    val exposedTransformationNames = objectFactory.setProperty(String::class.java)
}