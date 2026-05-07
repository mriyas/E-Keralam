package com.infoapp.tools

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.infoapp.core.di.FirebaseModule
import com.infoapp.domain.model.MenuItem

/**
 * ============================================================
 *  FirebaseSeeder.kt
 * ============================================================
 *  Inserts 36 dummy menu items into Firebase Realtime Database.
 *
 *  HOW TO USE:
 *  -----------
 *  Call FirebaseSeeder.seed() once from anywhere, e.g.:
 *
 *      // In MainActivity.onCreate() — run ONCE then remove
 *      FirebaseSeeder.seed(
 *          onSuccess = { Log.d("Seed", "Done!") },
 *          onError   = { Log.e("Seed", it.message ?: "error") }
 *      )
 *
 *  Structure seeded:
 *  -----------------
 *  Level 1 — 20 root items
 *  Level 2 — 11 sub-menu items  (inside News, Events, Travel, Technology, Sports)
 *  Level 3 — 10 inner items     (inside "Tech Courses" and "Sports Events")
 * ============================================================
 */
object FirebaseSeeder {

    private val db by lazy {
        FirebaseDatabase.getInstance(
            FirebaseModule.DATABASE_URL
        )
    }
    private val ref by lazy { db.getReference("menu_items") }

    private object Icons {
        const val NEWS = "https://cdn-icons-png.flaticon.com/512/2965/2965879.png"
        const val WORLD = "https://cdn-icons-png.flaticon.com/512/921/921490.png"
        const val LOCAL = "https://cdn-icons-png.flaticon.com/512/684/684908.png"
        const val BREAKING = "https://cdn-icons-png.flaticon.com/512/1041/1041916.png"
        const val POLITICS = "https://cdn-icons-png.flaticon.com/512/3135/3135789.png"
        const val OPINION = "https://cdn-icons-png.flaticon.com/512/1041/1041884.png"

        const val EVENTS = "https://cdn-icons-png.flaticon.com/512/2693/2693507.png"
        const val CONCERT = "https://cdn-icons-png.flaticon.com/512/3601/3601640.png"
        const val FESTIVAL = "https://cdn-icons-png.flaticon.com/512/3132/3132086.png"
        const val CONFERENCE = "https://cdn-icons-png.flaticon.com/512/1705/1705312.png"
        const val WORKSHOP = "https://cdn-icons-png.flaticon.com/512/2490/2490396.png"
        const val EXHIBITION = "https://cdn-icons-png.flaticon.com/512/3940/3940406.png"
        const val CHARITY = "https://cdn-icons-png.flaticon.com/512/2917/2917995.png"

        const val TRAVEL = "https://cdn-icons-png.flaticon.com/512/201/201623.png"
        const val BEACH = "https://cdn-icons-png.flaticon.com/512/1640/1640012.png"
        const val MOUNTAIN = "https://cdn-icons-png.flaticon.com/512/3240/3240638.png"
        const val CITY = "https://cdn-icons-png.flaticon.com/512/602/602175.png"
        const val BUDGET = "https://cdn-icons-png.flaticon.com/512/1995/1995567.png"

        const val HEALTH = "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"
        const val TECH = "https://cdn-icons-png.flaticon.com/512/2103/2103633.png"
        const val COURSES = "https://cdn-icons-png.flaticon.com/512/1995/1995574.png"
        const val SECURITY = "https://cdn-icons-png.flaticon.com/512/2092/2092663.png"
        const val BLOCKCHAIN = "https://cdn-icons-png.flaticon.com/512/2521/2521751.png"
        const val IOT = "https://cdn-icons-png.flaticon.com/512/3659/3659899.png"

        const val FRONTEND = "https://cdn-icons-png.flaticon.com/512/1336/1336494.png"
        const val BACKEND = "https://cdn-icons-png.flaticon.com/512/919/919853.png"
        const val MOBILE = "https://cdn-icons-png.flaticon.com/512/0/191.png"
        const val DEVOPS = "https://cdn-icons-png.flaticon.com/512/4248/4248443.png"
        const val AI_ML = "https://cdn-icons-png.flaticon.com/512/2103/2103652.png"

        const val SCIENCE = "https://cdn-icons-png.flaticon.com/512/3004/3004613.png"
        const val ENVIRONMENT = "https://cdn-icons-png.flaticon.com/512/2920/2920270.png"
        const val FOOD = "https://cdn-icons-png.flaticon.com/512/1046/1046784.png"
        const val CULTURE = "https://cdn-icons-png.flaticon.com/512/2306/2306154.png"
        const val FINANCE = "https://cdn-icons-png.flaticon.com/512/2376/2376432.png"

        const val SPORTS = "https://cdn-icons-png.flaticon.com/512/857/857418.png"
        const val FOOTBALL = "https://cdn-icons-png.flaticon.com/512/857/857441.png"
        const val BASKETBALL = "https://cdn-icons-png.flaticon.com/512/857/857474.png"
        const val TENNIS = "https://cdn-icons-png.flaticon.com/512/1158/1158148.png"
        const val CRICKET = "https://cdn-icons-png.flaticon.com/512/2972/2972353.png"
        const val MARATHON = "https://cdn-icons-png.flaticon.com/512/857/857455.png"
        const val GYM = "https://cdn-icons-png.flaticon.com/512/2964/2964541.png"

        const val MUSIC = "https://cdn-icons-png.flaticon.com/512/3659/3659784.png"
        const val MOVIES = "https://cdn-icons-png.flaticon.com/512/3073/3073665.png"
        const val FASHION = "https://cdn-icons-png.flaticon.com/512/2316/2316916.png"
        const val EDUCATION = "https://cdn-icons-png.flaticon.com/512/2436/2436882.png"
        const val BUSINESS = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
        const val PHOTOGRAPHY = "https://cdn-icons-png.flaticon.com/512/3940/3940406.png"
        const val GAMING = "https://cdn-icons-png.flaticon.com/512/3670/3670333.png"
        const val WEATHER = "https://cdn-icons-png.flaticon.com/512/1163/1163624.png"
        const val ABOUT = "https://cdn-icons-png.flaticon.com/512/1144/1144760.png"
    }

    // ── Data class that maps to the RTDB node ─────────────────────────────────
    data class MenuNode(
        val name: String = "",
        val iconUrl: String = "",
        val order: Int = 0,
        val parentId: String? = null,
        val subMenuIds: Map<String, Boolean> = emptyMap(),
        val text: String = "",
        val youtubeUrl: String = "",
        val imageUrl: String = "",
        val primaryButtonText: String = "",
        val primaryButtonAction: String = "",
    )

    private fun subIds(vararg ids: String): Map<String, Boolean> =
        ids.associateWith { true }

    private fun buildAllNodes(): Map<String, MenuNode> {

        val techCoursesChildren = mapOf(
            "tech_c_frontend" to MenuNode(
                name = "Frontend Development", iconUrl = Icons.FRONTEND, order = 1,
                parentId = "tech_courses",
                text = "Master HTML, CSS, JavaScript and React. Build beautiful, responsive UIs from the ground up.",
                youtubeUrl = "https://www.youtube.com/watch?v=ysEN5RaKOlA",
                imageUrl = "https://images.unsplash.com/photo-1547658719-da2b51169166?w=800",
                primaryButtonText = "Start Learning",
                primaryButtonAction = "https://www.freecodecamp.org",
            ),
            "tech_c_backend" to MenuNode(
                name = "Backend Development", iconUrl = Icons.BACKEND, order = 2,
                parentId = "tech_courses",
                text = "Learn Node.js, Python, databases, REST & GraphQL APIs and server-side architecture.",
                youtubeUrl = "https://www.youtube.com/watch?v=ENrzD9HAZK4",
                imageUrl = "https://images.unsplash.com/photo-1544256718-3bcf237f3974?w=800",
                primaryButtonText = "Start Learning",
                primaryButtonAction = "https://www.coursera.org/learn/server-side-nodejs",
            ),
            "tech_c_mobile" to MenuNode(
                name = "Mobile Development", iconUrl = Icons.MOBILE, order = 3,
                parentId = "tech_courses",
                text = "Build native and cross-platform mobile apps using Android (Kotlin/Compose), iOS (Swift) and Flutter.",
                youtubeUrl = "https://www.youtube.com/watch?v=F9UC9DY-vIU",
                imageUrl = "https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800",
                primaryButtonText = "Start Learning",
                primaryButtonAction = "https://developer.android.com/courses",
            ),
            "tech_c_devops" to MenuNode(
                name = "DevOps & Cloud", iconUrl = Icons.DEVOPS, order = 4,
                parentId = "tech_courses",
                text = "Understand CI/CD, Docker, Kubernetes, AWS and GCP to ship software faster and more reliably.",
                youtubeUrl = "https://www.youtube.com/watch?v=j5Zsa_eOXeY",
                imageUrl = "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=800",
                primaryButtonText = "Start Learning",
                primaryButtonAction = "https://www.udemy.com/topic/devops",
            ),
            "tech_c_aiml" to MenuNode(
                name = "AI & Machine Learning", iconUrl = Icons.AI_ML, order = 5,
                parentId = "tech_courses",
                text = "Dive into ML, deep learning and neural networks with Python, TensorFlow and PyTorch.",
                youtubeUrl = "https://www.youtube.com/watch?v=GwIo3gDZCVQ",
                imageUrl = "https://images.unsplash.com/photo-1677442135703-1787eea5ce01?w=800",
                primaryButtonText = "Start Learning",
                primaryButtonAction = "https://www.fast.ai",
            ),
        )

        val sportsEventChildren = mapOf(
            "sp_ev_football" to MenuNode(
                name = "Football Matches", iconUrl = Icons.FOOTBALL, order = 1,
                parentId = "sports_events",
                text = "Upcoming international and club football matches. Get fixtures, ticket info and live streaming links.",
                youtubeUrl = "https://www.youtube.com/watch?v=DJJFn-UVXMU",
                imageUrl = "https://images.unsplash.com/photo-1571008887538-b36bb32f4571?w=800",
                primaryButtonText = "Buy Tickets",
                primaryButtonAction = "https://www.fifa.com/tickets",
            ),
            "sp_ev_basketball" to MenuNode(
                name = "Basketball Games", iconUrl = Icons.BASKETBALL, order = 2,
                parentId = "sports_events",
                text = "NBA, EuroLeague and local basketball fixtures. Find venues, timings and broadcast information.",
                youtubeUrl = "https://www.youtube.com/watch?v=hiZwaQ_XULU",
                imageUrl = "https://images.unsplash.com/photo-1546519638-68e109498ffc?w=800",
                primaryButtonText = "View Schedule",
                primaryButtonAction = "https://www.nba.com/schedule",
            ),
            "sp_ev_tennis" to MenuNode(
                name = "Tennis Tournaments", iconUrl = Icons.TENNIS, order = 3,
                parentId = "sports_events",
                text = "Grand Slams, ATP and WTA tour events — schedules, draws and ticket information.",
                youtubeUrl = "https://www.youtube.com/watch?v=7uG7iGBjIlw",
                imageUrl = "https://images.unsplash.com/photo-1554068865-24cecd4e34b8?w=800",
                primaryButtonText = "View Schedule",
                primaryButtonAction = "https://www.atptour.com",
            ),
            "sp_ev_cricket" to MenuNode(
                name = "Cricket Series", iconUrl = Icons.CRICKET, order = 4,
                parentId = "sports_events",
                text = "International Test series, ODIs and T20Is — complete fixtures and live score links.",
                youtubeUrl = "https://www.youtube.com/watch?v=L2yAP6R4wEk",
                imageUrl = "https://images.unsplash.com/photo-1531415074968-036ba1b575da?w=800",
                primaryButtonText = "View Schedule",
                primaryButtonAction = "https://www.icc-cricket.com/fixtures",
            ),
            "sp_ev_marathon" to MenuNode(
                name = "Marathon Races", iconUrl = Icons.MARATHON, order = 5,
                parentId = "sports_events",
                text = "World major marathons — Berlin, London, Tokyo, New York, Chicago and Boston. Register today!",
                youtubeUrl = "https://www.youtube.com/watch?v=HdNb7xXFyg4",
                imageUrl = "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=800",
                primaryButtonText = "Register Now",
                primaryButtonAction = "https://worldathletics.org/competition/calendar",
            ),
        )

        val level2 = mapOf(
            // News children
            "news_world" to MenuNode(
                name = "World News",
                iconUrl = Icons.WORLD,
                order = 1,
                parentId = "root_news",
                text = "Global headlines from every continent. Stay connected to international events.",
                youtubeUrl = "https://www.youtube.com/watch?v=3gK_2XDpKr4",
                imageUrl = "https://images.unsplash.com/photo-1495020689067-958852a7765e?w=800",
                primaryButtonText = "Read More",
                primaryButtonAction = "https://www.bbc.com/news/world",
            ),
            "news_local" to MenuNode(
                name = "Local News", iconUrl = Icons.LOCAL, order = 2,
                parentId = "root_news",
                text = "Breaking news, community updates and stories from your city and surrounding region.",
                imageUrl = "https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=800",
                primaryButtonText = "Read More", primaryButtonAction = "https://news.google.com",
            ),
            "news_breaking" to MenuNode(
                name = "Breaking News", iconUrl = Icons.BREAKING, order = 3,
                parentId = "root_news",
                text = "Live breaking news as it happens. Refresh for the latest updates on major stories.",
                youtubeUrl = "https://www.youtube.com/watch?v=3gK_2XDpKr4",
                primaryButtonText = "Follow Live", primaryButtonAction = "https://www.reuters.com",
            ),
            "news_politics" to MenuNode(
                name = "Politics", iconUrl = Icons.POLITICS, order = 4,
                parentId = "root_news",
                text = "In-depth political analysis, election coverage and government policy updates.",
                imageUrl = "https://images.unsplash.com/photo-1529107386315-e1a2ed48a620?w=800",
                primaryButtonText = "Read More", primaryButtonAction = "https://www.politico.com",
            ),
            "news_opinion" to MenuNode(
                name = "Opinion & Editorial",
                iconUrl = Icons.OPINION,
                order = 5,
                parentId = "root_news",
                text = "Thoughtful opinion pieces and editorial commentary on the issues that matter most.",
                imageUrl = "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=800",
                primaryButtonText = "Read More",
                primaryButtonAction = "https://www.theguardian.com/commentisfree",
            ),
            // Events children
            "ev_concert" to MenuNode(
                name = "Concerts",
                iconUrl = Icons.CONCERT,
                order = 1,
                parentId = "root_events",
                text = "Upcoming live music concerts from top artists worldwide. Find tickets, venues and set-list previews.",
                youtubeUrl = "https://www.youtube.com/watch?v=SlPhMPnQ58k",
                imageUrl = "https://images.unsplash.com/photo-1501281668745-f7f57925c3b4?w=800",
                primaryButtonText = "Get Tickets",
                primaryButtonAction = "https://www.ticketmaster.com",
            ),
            "ev_festival" to MenuNode(
                name = "Festivals", iconUrl = Icons.FESTIVAL, order = 2,
                parentId = "root_events",
                text = "Art, music, food and cultural festivals happening near you and around the world this season.",
                imageUrl = "https://images.unsplash.com/photo-1533174072545-7a4b6ad7a6c3?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.eventbrite.com",
            ),
            "ev_conference" to MenuNode(
                name = "Conferences",
                iconUrl = Icons.CONFERENCE,
                order = 3,
                parentId = "root_events",
                text = "Professional conferences, summits and industry gatherings with networking opportunities.",
                imageUrl = "https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=800",
                primaryButtonText = "Register",
                primaryButtonAction = "https://www.eventbrite.com/d/online/conference",
            ),
            "ev_workshop" to MenuNode(
                name = "Workshops", iconUrl = Icons.WORKSHOP, order = 4,
                parentId = "root_events",
                text = "Hands-on workshops and masterclasses across tech, art, business and personal development.",
                imageUrl = "https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=800",
                primaryButtonText = "Sign Up", primaryButtonAction = "https://www.meetup.com",
            ),
            "ev_exhibition" to MenuNode(
                name = "Exhibitions",
                iconUrl = Icons.EXHIBITION,
                order = 5,
                parentId = "root_events",
                text = "Art, photography, science and trade exhibitions from galleries and museums around the world.",
                imageUrl = "https://images.unsplash.com/photo-1531685250784-7569952593d2?w=800",
                primaryButtonText = "Book Visit",
                primaryButtonAction = "https://www.timeout.com/art",
            ),
            "ev_charity" to MenuNode(
                name = "Charity Events",
                iconUrl = Icons.CHARITY,
                order = 6,
                parentId = "root_events",
                text = "Make a difference. Charity runs, galas, auctions and fundraising drives that need your support.",
                imageUrl = "https://images.unsplash.com/photo-1532629345422-7515f3d16bb6?w=800",
                primaryButtonText = "Donate",
                primaryButtonAction = "https://www.charitynavigator.org",
            ),
            // Travel children
            "travel_beach" to MenuNode(
                name = "Beach Getaways", iconUrl = Icons.BEACH, order = 1,
                parentId = "root_travel",
                text = "Sun, sand and sea — curated beach destinations from the Maldives to the Caribbean.",
                youtubeUrl = "https://www.youtube.com/watch?v=yMqP8Mm3jHs",
                imageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800",
                primaryButtonText = "Book Now", primaryButtonAction = "https://www.booking.com",
            ),
            "travel_mountain" to MenuNode(
                name = "Mountain Retreats", iconUrl = Icons.MOUNTAIN, order = 2,
                parentId = "root_travel",
                text = "Breathtaking alpine adventures — hiking, skiing and scenic retreats in the world's greatest ranges.",
                youtubeUrl = "https://www.youtube.com/watch?v=7D-cHMTm_5M",
                imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.alltrails.com",
            ),
            "travel_city" to MenuNode(
                name = "City Breaks",
                iconUrl = Icons.CITY,
                order = 3,
                parentId = "root_travel",
                text = "Weekend city breaks in the world's most exciting urban destinations.",
                imageUrl = "https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?w=800",
                primaryButtonText = "Plan Trip",
                primaryButtonAction = "https://www.tripadvisor.com",
            ),
            "travel_budget" to MenuNode(
                name = "Budget Travel",
                iconUrl = Icons.BUDGET,
                order = 4,
                parentId = "root_travel",
                text = "Travel the world without breaking the bank. Expert tips on cheap flights and hostels.",
                imageUrl = "https://images.unsplash.com/photo-1488646953014-85cb44e25828?w=800",
                primaryButtonText = "Find Deals",
                primaryButtonAction = "https://www.skyscanner.com",
            ),
            // Technology children  (tech_courses points to level 3)
            "tech_latest" to MenuNode(
                name = "Latest Tech", iconUrl = Icons.TECH, order = 1,
                parentId = "root_technology",
                text = "The hottest new gadgets, software releases and tech industry news. Stay ahead of the curve.",
                youtubeUrl = "https://www.youtube.com/watch?v=Lk2-UpMFIaY",
                imageUrl = "https://images.unsplash.com/photo-1518770660439-4636190af475?w=800",
                primaryButtonText = "Read More", primaryButtonAction = "https://www.theverge.com",
            ),
            "tech_courses" to MenuNode(
                name = "Tech Courses", iconUrl = Icons.COURSES, order = 2,
                parentId = "root_technology",
                subMenuIds = subIds(*techCoursesChildren.keys.toTypedArray()),  // ← level 3
            ),
            "tech_security" to MenuNode(
                name = "Cybersecurity",
                iconUrl = Icons.SECURITY,
                order = 3,
                parentId = "root_technology",
                text = "Latest cybersecurity news, vulnerability alerts and best practices for individuals and businesses.",
                youtubeUrl = "https://www.youtube.com/watch?v=inWWhr5tnEA",
                imageUrl = "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800",
                primaryButtonText = "Learn More",
                primaryButtonAction = "https://www.cybersecurity.gov",
            ),
            "tech_blockchain" to MenuNode(
                name = "Blockchain & Web3", iconUrl = Icons.BLOCKCHAIN, order = 4,
                parentId = "root_technology",
                text = "Decentralised finance, NFTs, smart contracts and the evolving Web3 ecosystem explained simply.",
                imageUrl = "https://images.unsplash.com/photo-1639762681485-074b7f938ba0?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.coindesk.com",
            ),
            "tech_iot" to MenuNode(
                name = "IoT & Smart Home", iconUrl = Icons.IOT, order = 5,
                parentId = "root_technology",
                text = "Smart devices, home automation, wearables and the Internet of Things — news, reviews and guides.",
                imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.iotforall.com",
            ),
            // Sports children  (sports_events points to level 3)
            "sports_news_sub" to MenuNode(
                name = "Sports News", iconUrl = Icons.SPORTS, order = 1,
                parentId = "root_sports",
                text = "Latest transfers, injury updates, match previews and post-match analysis across all major sports.",
                imageUrl = "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=800",
                primaryButtonText = "Read More", primaryButtonAction = "https://www.espn.com",
            ),
            "sports_events" to MenuNode(
                name = "Sports Events", iconUrl = Icons.FOOTBALL, order = 2,
                parentId = "root_sports",
                subMenuIds = subIds(*sportsEventChildren.keys.toTypedArray()),  // ← level 3
            ),
            "sports_fitness" to MenuNode(
                name = "Fitness Tips", iconUrl = Icons.GYM, order = 3,
                parentId = "root_sports",
                text = "Workout plans, nutrition advice and recovery techniques for every fitness level.",
                youtubeUrl = "https://www.youtube.com/watch?v=vc1E5CfRfos",
                imageUrl = "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=800",
                primaryButtonText = "Get Fit", primaryButtonAction = "https://www.myfitnesspal.com",
            ),
            "sports_results" to MenuNode(
                name = "Results & Stats",
                iconUrl = Icons.BASKETBALL,
                order = 4,
                parentId = "root_sports",
                text = "Live scores, historical results, league tables and player statistics for all major sports.",
                primaryButtonText = "View Stats",
                primaryButtonAction = "https://www.flashscore.com",
            ),
            "sports_fantasy" to MenuNode(
                name = "Fantasy Sports",
                iconUrl = Icons.SPORTS,
                order = 5,
                parentId = "root_sports",
                text = "Build your dream team and compete with friends in fantasy football, cricket and basketball leagues.",
                imageUrl = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=800",
                primaryButtonText = "Play Now",
                primaryButtonAction = "https://fantasy.premierleague.com",
            ),
        )

        val level1 = mapOf(
            "root_news" to MenuNode(
                name = "News", iconUrl = Icons.NEWS, order = 1,
                subMenuIds = subIds(
                    "news_world",
                    "news_local",
                    "news_breaking",
                    "news_politics",
                    "news_opinion"
                ),
            ),
            "root_events" to MenuNode(
                name = "Events", iconUrl = Icons.EVENTS, order = 2,
                subMenuIds = subIds(
                    "ev_concert",
                    "ev_festival",
                    "ev_conference",
                    "ev_workshop",
                    "ev_exhibition",
                    "ev_charity"
                ),
            ),
            "root_travel" to MenuNode(
                name = "Travel", iconUrl = Icons.TRAVEL, order = 3,
                subMenuIds = subIds(
                    "travel_beach",
                    "travel_mountain",
                    "travel_city",
                    "travel_budget"
                ),
            ),
            "root_health" to MenuNode(
                name = "Health & Wellness",
                iconUrl = Icons.HEALTH,
                order = 4,
                text = "Expert advice on physical health, mental wellness and nutrition to help you live your best life.",
                youtubeUrl = "https://www.youtube.com/watch?v=Hpfx0tC-esU",
                imageUrl = "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?w=800",
                primaryButtonText = "Learn More",
                primaryButtonAction = "https://www.who.int/health-topics",
            ),
            "root_technology" to MenuNode(
                name = "Technology", iconUrl = Icons.TECH, order = 5,
                subMenuIds = subIds(
                    "tech_latest",
                    "tech_courses",
                    "tech_security",
                    "tech_blockchain",
                    "tech_iot"
                ),
            ),
            "root_science" to MenuNode(
                name = "Science", iconUrl = Icons.SCIENCE, order = 6,
                text = "Discoveries and breakthroughs from space exploration, biology, physics and earth sciences.",
                youtubeUrl = "https://www.youtube.com/watch?v=MX3Pi5haWFY",
                imageUrl = "https://images.unsplash.com/photo-1507413245164-6160d8298b31?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.nature.com",
            ),
            "root_environment" to MenuNode(
                name = "Environment", iconUrl = Icons.ENVIRONMENT, order = 7,
                text = "Climate change, sustainability, conservation and green living tips to protect our planet.",
                youtubeUrl = "https://www.youtube.com/watch?v=G4H1N_yXBiA",
                imageUrl = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=800",
                primaryButtonText = "Take Action", primaryButtonAction = "https://www.unep.org",
            ),
            "root_food" to MenuNode(
                name = "Food & Recipes",
                iconUrl = Icons.FOOD,
                order = 8,
                text = "Delicious recipes, restaurant reviews, cooking techniques and food culture from around the world.",
                youtubeUrl = "https://www.youtube.com/watch?v=pM4E8pBCpJo",
                imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800",
                primaryButtonText = "Browse Recipes",
                primaryButtonAction = "https://www.allrecipes.com",
            ),
            "root_culture" to MenuNode(
                name = "Culture & Arts",
                iconUrl = Icons.CULTURE,
                order = 9,
                text = "Art, literature, history and philosophy shaping our collective identity.",
                imageUrl = "https://images.unsplash.com/photo-1578301978693-85fa9c0320b9?w=800",
                primaryButtonText = "Explore",
                primaryButtonAction = "https://www.theartnewspaper.com",
            ),
            "root_finance" to MenuNode(
                name = "Finance",
                iconUrl = Icons.FINANCE,
                order = 10,
                text = "Personal finance tips, stock market updates and investment strategies for smart money management.",
                youtubeUrl = "https://www.youtube.com/watch?v=HQzoZfc3GwQ",
                imageUrl = "https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800",
                primaryButtonText = "Learn More",
                primaryButtonAction = "https://www.investopedia.com",
            ),
            "root_sports" to MenuNode(
                name = "Sports", iconUrl = Icons.SPORTS, order = 11,
                subMenuIds = subIds(
                    "sports_news_sub",
                    "sports_events",
                    "sports_fitness",
                    "sports_results",
                    "sports_fantasy"
                ),
            ),
            "root_music" to MenuNode(
                name = "Music", iconUrl = Icons.MUSIC, order = 12,
                text = "New releases, album reviews, artist spotlights, music history and concert guides.",
                youtubeUrl = "https://www.youtube.com/watch?v=SlPhMPnQ58k",
                imageUrl = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=800",
                primaryButtonText = "Listen Now", primaryButtonAction = "https://open.spotify.com",
            ),
            "root_movies" to MenuNode(
                name = "Movies & TV", iconUrl = Icons.MOVIES, order = 13,
                text = "Box office news, TV show recaps, trailers, reviews and streaming recommendations.",
                youtubeUrl = "https://www.youtube.com/watch?v=3gK_2XDpKr4",
                imageUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=800",
                primaryButtonText = "Browse", primaryButtonAction = "https://www.imdb.com",
            ),
            "root_fashion" to MenuNode(
                name = "Fashion", iconUrl = Icons.FASHION, order = 14,
                text = "Fashion weeks, trend reports, style guides and sustainable fashion news.",
                imageUrl = "https://images.unsplash.com/photo-1445205170230-053b83016050?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.vogue.com",
            ),
            "root_education" to MenuNode(
                name = "Education",
                iconUrl = Icons.EDUCATION,
                order = 15,
                text = "Learning resources, scholarship opportunities and education tips for students of all ages.",
                youtubeUrl = "https://www.youtube.com/watch?v=V75dMMIW2B4",
                imageUrl = "https://images.unsplash.com/photo-1509062522246-3755977927d7?w=800",
                primaryButtonText = "Start Learning",
                primaryButtonAction = "https://www.khanacademy.org",
            ),
            "root_business" to MenuNode(
                name = "Business", iconUrl = Icons.BUSINESS, order = 16,
                text = "Entrepreneurship, startups, corporate news and management strategies for leaders.",
                imageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=800",
                primaryButtonText = "Read More", primaryButtonAction = "https://www.hbr.org",
            ),
            "root_photography" to MenuNode(
                name = "Photography", iconUrl = Icons.PHOTOGRAPHY, order = 17,
                text = "Photography tutorials, gear reviews, editing techniques and inspiration galleries.",
                youtubeUrl = "https://www.youtube.com/watch?v=LxO-6rlihSg",
                imageUrl = "https://images.unsplash.com/photo-1502982720700-bfff97f2ecac?w=800",
                primaryButtonText = "Get Inspired", primaryButtonAction = "https://www.500px.com",
            ),
            "root_gaming" to MenuNode(
                name = "Gaming", iconUrl = Icons.GAMING, order = 18,
                text = "Game reviews, esports coverage, release dates and hardware recommendations for all platforms.",
                youtubeUrl = "https://www.youtube.com/watch?v=rQgg6NM_MNc",
                imageUrl = "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=800",
                primaryButtonText = "Explore", primaryButtonAction = "https://www.ign.com",
            ),
            "root_weather" to MenuNode(
                name = "Weather",
                iconUrl = Icons.WEATHER,
                order = 19,
                text = "Live weather forecasts, storm alerts and climate data for locations around the world.",
                primaryButtonText = "Check Weather",
                primaryButtonAction = "https://www.weather.com",
            ),
            "root_about" to MenuNode(
                name = "About Us",
                iconUrl = Icons.ABOUT,
                order = 20,
                text = "We are a passionate team delivering quality information to our users every day.\n\nFounded in 2024, we have served over 100,000 users across 50 countries.",
                youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                imageUrl = "https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=800",
                primaryButtonText = "Visit Website",
                primaryButtonAction = "https://example.com/about",
            ),
        )

        return level1 + level2 + techCoursesChildren + sportsEventChildren
    }

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Writes all 36 dummy nodes to /menu_items in Firebase Realtime Database.
     *
     * Call ONCE (e.g. from MainActivity.onCreate) and then remove.
     *
     * @param onSuccess callback when all data is written successfully
     * @param onError   callback when writing fails
     */
    fun seed(
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {},
    ) {
        val allNodes = buildAllNodes()
        runCatching {
            ref.child("user1").setValue("Hello Firebase")
                .addOnSuccessListener {
                    Log.d("Firebase", "Success")
                }.addOnFailureListener {
                    Log.e("Firebase", "Failed", it)
                }
            ref.setValue(allNodes)
                .addOnSuccessListener {
                    Log.d("FirebaseSeeder", "✅ Seeded ${allNodes.size} nodes successfully!")
                    Log.d("FirebaseSeeder", buildSummary(allNodes))
                    onSuccess()
                }
                .addOnFailureListener { error ->
                    Log.e("FirebaseSeeder", "❌ Seed failed: ${error.message}")
                    onError(error)
                }
        }.onFailure { error ->
            Log.e("FirebaseSeeder", "❌ Seed failed: ${error.message}")
            onError(RuntimeException(error.message))
        }

    }

    private fun buildSummary(all: Map<String, MenuNode>): String {
        val level1Count = all.values.count { it.parentId == null }
        val level2Count = all.values.count { node ->
            node.parentId != null && all[node.parentId]?.parentId == null
        }
        val level3Count = all.values.count { node ->
            node.parentId != null && all[node.parentId]?.parentId != null
        }
        return """
            
            ══════════════════════════════════
             Firebase Seed Summary
            ══════════════════════════════════
             Level 1 (root)   : $level1Count items
             Level 2 (sub)    : $level2Count items
             Level 3 (inner)  : $level3Count items
             Total            : ${all.size} items
            ══════════════════════════════════
            
            Tree:
             Dashboard (20 root items)
             ├── News        → 5 children
             ├── Events      → 6 children
             ├── Travel      → 4 children
             ├── Technology  → 5 children
             │     └── Tech Courses → 5 inner items  [Level 3]
             └── Sports      → 5 children
                   └── Sports Events → 5 inner items  [Level 3]
        """.trimIndent()
    }
}
