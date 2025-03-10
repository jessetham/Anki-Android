/*
 *  Copyright (c) 2021 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.libanki

import android.content.Context
import com.ichi2.libanki.backend.RustConfigBackend
import com.ichi2.libanki.backend.RustDroidDeckBackend
import com.ichi2.libanki.backend.RustDroidV16Backend
import com.ichi2.libanki.backend.RustTagsBackend
import com.ichi2.libanki.utils.Time

class CollectionV16(
    context: Context,
    db: DB,
    path: String?,
    server: Boolean,
    log: Boolean,
    time: Time,
    backend: RustDroidV16Backend
) : Collection(context, db, path, server, log, time, backend) {

    /** Workaround as we shouldn't be overriding members which are used in the constructor */
    override fun getBackend(): RustDroidV16Backend {
        return super.getBackend() as RustDroidV16Backend
    }

    override fun initTags(): TagManager {
        return TagsV16(this, RustTagsBackend(backend.backend))
    }

    override fun initDecks(deckConf: String?): DeckManager {
        return DecksV16(this, RustDroidDeckBackend(backend.backend))
    }

    override fun initModels(): ModelManager {
        return ModelsV16(this, backend.backend)
    }

    override fun initConf(conf: String): ConfigManager {
        return ConfigV16(RustConfigBackend(backend.backend))
    }
}
