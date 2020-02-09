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

package org.jraf.klibappstorerating

import org.jraf.klibappstorerating.internal.appleappstore.AppleAppStoreClient
import org.jraf.klibappstorerating.internal.googleplaystore.GooglePlayStoreClient

object KLibAppStoreRating {
    /**
     * Retrieve the rating for the given [appId] on the given [appStore].
     * @throws RatingRetrievalException when rating retrieval fails.
     */
    @Throws(RatingRetrievalException::class)
    suspend fun retrieveRating(appStore: AppStore, appId: String): Float {
        return try {
            buildAppStoreClient(appStore).retrieveRating(appId)
        } catch (t: Throwable) {
            throw RatingRetrievalException(t)
        }
    }

    private fun buildAppStoreClient(appStore: AppStore) = when (appStore) {
        AppStore.APPLE_APP_STORE -> AppleAppStoreClient()
        AppStore.GOOGLE_PLAY_STORE -> GooglePlayStoreClient()
    }
}