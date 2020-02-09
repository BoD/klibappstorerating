/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2020-present Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jraf.klibappstorerating.sample

import org.jraf.klibappstorerating.AppStore
import org.jraf.klibappstorerating.KLibAppStoreRating

suspend fun main() {
    val googlePlayStoreAppId = "org.jraf.android.latoureiffel"
    val googlePlayStoreRating = KLibAppStoreRating.retrieveRating(
        AppStore.GOOGLE_PLAY_STORE,
        googlePlayStoreAppId
    )
    println("Rating on Google Play Store for $googlePlayStoreAppId is $googlePlayStoreRating")

    val appleAppStoreAppId = "id1214811644"
    val appleAppStoreRating = KLibAppStoreRating.retrieveRating(
        AppStore.APPLE_APP_STORE,
        appleAppStoreAppId
    )
    println("Rating on Apple App Store for $appleAppStoreAppId is $appleAppStoreRating")
}