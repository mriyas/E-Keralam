package com.infoapp.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.PropertyName
import com.infoapp.domain.model.MenuItem

/**
 * Firebase Realtime Database DTO.
 *
 * Database node  /menu_items/<id>:
 * {
 *   "name": "News",
 *   "iconUrl": "https://...",
 *   "order": 1,
 *   "parentId": null,
 *   "subMenuIds": { "child_id_1": true, "child_id_2": true },
 *   "text": "...",
 *   "youtubeUrl": "...",
 *   "imageUrl": "...",
 *   "primaryButtonText": "...",
 *   "primaryButtonAction": "..."
 * }
 *
 * No-arg constructor required by the Firebase RTDB SDK.
 */
data class MenuItemDto(
    @get:PropertyName("name")               @set:PropertyName("name")               var name: String = "",
    @get:PropertyName("iconUrl")            @set:PropertyName("iconUrl")            var iconUrl: String = "",
    @get:PropertyName("order")              @set:PropertyName("order")              var order: Int = 0,
    @get:PropertyName("parentId")           @set:PropertyName("parentId")           var parentId: String? = null,
    @get:PropertyName("text")               @set:PropertyName("text")               var text: String = "",
    @get:PropertyName("youtubeUrl")         @set:PropertyName("youtubeUrl")         var youtubeUrl: String = "",
    @get:PropertyName("imageUrl")           @set:PropertyName("imageUrl")           var imageUrl: String = "",
    @get:PropertyName("primaryButtonText")  @set:PropertyName("primaryButtonText")  var primaryButtonText: String = "",
    @get:PropertyName("primaryButtonAction")@set:PropertyName("primaryButtonAction")var primaryButtonAction: String = "",
    // RTDB doesn't support plain arrays reliably → use Map<childId, true>
    @get:PropertyName("subMenuIds")         @set:PropertyName("subMenuIds")         var subMenuIds: Map<String, Boolean> = emptyMap()
) {
    fun toDomain(id: String): MenuItem = MenuItem(
        id                  = id,
        name                = name,
        iconUrl             = iconUrl,
        order               = order,
        parentId            = parentId,
        subMenuIds          = subMenuIds.keys.toList(),
        text                = text,
        youtubeUrl          = youtubeUrl,
        imageUrl            = imageUrl,
        primaryButtonText   = primaryButtonText,
        primaryButtonAction = primaryButtonAction
    )

    companion object {
        fun fromSnapshot(snapshot: DataSnapshot): Pair<String, MenuItemDto>? {
            val dto = snapshot.getValue(MenuItemDto::class.java) ?: return null
            return snapshot.key!! to dto
        }
    }
}
