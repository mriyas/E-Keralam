# E-Keralam — All Kerala government services at your finger tip

A clean, production-ready Android app that reads all menu/content configuration from
**Firebase Realtime Database** and renders a drill-down grid dashboard with rich content screens.

---

## ✨ Features

- 🔥 **Firebase Realtime Database** — real-time sync, offline persistence enabled
- 🏗️ **Clean Architecture** — Data → Domain → Presentation layers
- 🧩 **MVVM + Use Cases** — strict separation of concerns
- 🎨 **Jetpack Compose + Material 3** custom theming
- 🌀 **Animated splash screen** (AndroidX SplashScreen API)
- 📐 **Unlimited drill-down grid** navigation (Level 1 → 2 → 3 → …)
- 📺 **YouTube player** embedded in content screens
- 🖼️ **Coil** async image loading with crossfade
- 💉 **Hilt** dependency injection
- ✨ **Shimmer loading skeletons** while data loads
- 🔄 **Spring-animated card entrance** per grid item
- 📶 **Offline support** via RTDB disk persistence

---

## 🏛️ Architecture

Architecture Daigram : https://excalidraw.com/#json=8xUcwxmW8zbcCpjibTFrd,woH4dirRmq64xov1uq0yiQ

```
app/src/main/java/com/infoapp/
├── core/
│   ├── di/           AppModule.kt       ← Hilt: provides FirebaseDatabase
│   ├── network/      AppNavGraph.kt     ← Navigation Compose host
│   └── utils/        Screen.kt  YouTubeUtils.kt
├── data/
│   ├── remote/       FirebaseMenuDataSource.kt   ← RTDB ValueEventListeners
│   │                 MenuItemDto.kt              ← RTDB-compatible DTO
│   └── repository/   MenuRepositoryImpl.kt
├── domain/
│   ├── model/        MenuItem.kt                 ← Pure Kotlin
│   ├── repository/   MenuRepository.kt           ← Interface
│   └── usecase/      MenuUseCases.kt             ← 3 use cases
└── presentation/
    ├── splash/       SplashScreen.kt
    ├── dashboard/    MenuGridScreen.kt  MenuGridViewModel.kt
    ├── detail/       DetailScreen.kt    DetailViewModel.kt   YoutubePlayerComposable.kt
    ├── components/   CommonComponents.kt  (shimmer, error, loading)
    └── theme/        Color.kt  Type.kt  Theme.kt
```

---

## 🗄️ Realtime Database Structure

```
/menu_items
  ├── root_news
  │     name: "News"
  │     iconUrl: "https://..."
  │     order: 1
  │     parentId: null
  │     subMenuIds: { "news_world": true, "news_local": true, … }
  │     text: ""
  │     youtubeUrl: ""
  │     imageUrl: ""
  │     primaryButtonText: ""
  │     primaryButtonAction: ""
  │
  ├── news_world
  │     name: "World News"
  │     parentId: "root_news"
  │     subMenuIds: {}           ← empty = content leaf
  │     text: "Global headlines…"
  │     youtubeUrl: "https://youtube.com/…"
  │     imageUrl: "https://…"
  │     primaryButtonText: "Read More"
  │     primaryButtonAction: "https://bbc.com/news/world"
  │
  └── … (all items flat under /menu_items)
```

> **Why flat structure?**  
> RTDB works best with flat/denormalised data. All items live at `/menu_items/<id>`.
> Parent–child relationships use `parentId` (for querying children) and
> `subMenuIds` map (for knowing a node has children without extra queries).

### Field Reference

| Field | Type | Description |
|-------|------|-------------|
| `name` | String | Display name on card |
| `iconUrl` | String | Image URL for the icon |
| `order` | Number | Sort position in the grid |
| `parentId` | String\|null | `null` = root item; otherwise parent's key |
| `subMenuIds` | Map\<String,Boolean\> | Keys = child item IDs; empty = leaf node |
| `text` | String | Body text shown on detail screen |
| `youtubeUrl` | String | YouTube video URL (omit or `""` = no player) |
| `imageUrl` | String | Content image URL (omit or `""` = no image) |
| `primaryButtonText` | String | CTA button label e.g. "Book Ticket" |
| `primaryButtonAction` | String | URL opened on CTA tap |

---

## 🚀 Setup Instructions

### 1. Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project
3. Add an **Android** app — package name: `com.infoapp`
4. Download `google-services.json` → place in `app/`
5. Enable **Realtime Database** → Start in **test mode** for development

### 2. RTDB URL

Firebase auto-assigns a database URL like:
```
https://<project-id>-default-rtdb.firebaseio.com
```
The app uses `FirebaseDatabase.getInstance()` which picks this up automatically
from `google-services.json`. No manual URL needed.

### 3. Seed Dummy Data

```kotlin
// ⚠️ Run ONCE to seed data, then delete these lines
FirebaseSeeder.seed(
    onSuccess = { Log.d("Seed", "✅ Done!") },
    onError   = { Log.e("Seed", "❌ ${it.message}") }
)
```

The seed script writes **36 nodes** organised as:

```
Dashboard (20 root items)
├── 📰 News            → 5 sub-items  (World, Local, Breaking, Politics, Opinion)
├── 🎭 Events          → 6 sub-items  (Concerts, Festivals, Conferences, Workshops, …)
├── ✈️  Travel          → 4 sub-items  (Beach, Mountain, City, Budget)
├── 💚 Health          → (content leaf)
├── 💻 Technology      → 5 sub-items
│       └── Tech Courses → 5 inner items  ← LEVEL 3
│               (Frontend, Backend, Mobile, DevOps, AI/ML)
├── 🔬 Science         → (content leaf)
├── 🌿 Environment     → (content leaf)
├── 🍔 Food & Recipes  → (content leaf)
├── 🎨 Culture & Arts  → (content leaf)
├── 💰 Finance         → (content leaf)
├── 🏅 Sports          → 5 sub-items
│       └── Sports Events → 5 inner items  ← LEVEL 3
│               (Football, Basketball, Tennis, Cricket, Marathon)
├── 🎵 Music           → (content leaf)
├── 🎬 Movies & TV     → (content leaf)
├── 👗 Fashion         → (content leaf)
├── 📚 Education       → (content leaf)
├── 💼 Business        → (content leaf)
├── 📸 Photography     → (content leaf)
├── 🎮 Gaming          → (content leaf)
├── 🌤  Weather        → (content leaf)
└── ℹ️  About Us       → (content leaf)
```

### 4. RTDB Indexing Rule

Add this to your **Realtime Database Rules** so the `parentId` query is indexed:

```json
{
  "rules": {
    "menu_items": {
      ".read": true,
      ".write": "auth != null",
      ".indexOn": ["parentId", "order"]
    }
  }
}
```

### 5. Build & Run

Open in **Android Studio Ladybug (2024.2+)**, sync Gradle, run on device or emulator.

```bash
./gradlew assembleDebug
```

---

## 🗺️ Navigation Flow

```
Splash Screen
    │
    ▼
Dashboard Grid  (root items, parentId = null)
    │
    ├─ tap item with subMenuIds  ──▶  Sub-Menu Grid  (parentId = item.id)
    │                                      │
    │                                      ├─ tap item with subMenuIds  ──▶  Inner Grid (Level 3)
    │                                      │                                       │
    │                                      │                                       └─ tap leaf  ──▶  Detail Screen
    │                                      │
    │                                      └─ tap leaf item  ──▶  Detail Screen
    │
    └─ tap leaf item  ──▶  Detail Screen
                               │
                               └─ primaryButtonAction  ──▶  External URL (browser/app)
```

The same `MenuGridScreen` composable handles all grid levels; only `menuId` nav arg changes.

---

## 📦 Key Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| AGP | **8.9.0** | Android Gradle Plugin |
| Kotlin | **2.1.0** | Language |
| Compose BOM | 2025.02.00 | UI toolkit |
| Material 3 | 1.3.1 | Design system |
| Navigation Compose | 2.8.9 | Screen routing |
| Hilt | 2.55 | Dependency injection |
| Firebase BOM | 33.10.0 | Firebase platform |
| **Firebase RTDB KTX** | — | **Realtime Database** |
| Coil Compose | 2.7.0 | Async image loading |
| YouTube Player | 12.1.0 | Embedded video |
| Coroutines | 1.10.1 | Async / Flow |
| AndroidX SplashScreen | 1.0.1 | Splash API |

---

## 🔐 Production Security Rules

```json
{
  "rules": {
    "menu_items": {
      ".read": true,
      ".write": "auth != null",
      ".indexOn": ["parentId", "order"]
    }
  }
}
```
