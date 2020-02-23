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

package org.jraf.klibappstorerating.internal.appleappstore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jraf.klibappstorerating.internal.AppStoreClient
import java.net.URL

internal class AppleAppStoreClient : AppStoreClient {
    override suspend fun retrieveRating(appId: String): Float {
        var text = withContext(Dispatchers.IO) { URL(getStorePageUrl(appId)).readText() }
        text = text.substringAfter(DELIM_0).substringBefore(DELIM_1)
        return text.toFloat()
    }

    override fun getStorePageUrl(appId: String) = URL_APP_PAGE.format(appId)

    companion object {
        private const val URL_APP_PAGE = "https://itunes.apple.com/fr/app/%1\$s"
        private const val DELIM_0 = "\"ratingValue\":"
        private const val DELIM_1 = ","
    }
}