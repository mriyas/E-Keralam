/**
 * ============================================================
 *  InfoApp — Firebase Realtime Database Seed Script
 * ============================================================
 *  Data shape
 *  ----------
 *  • 20 root menu items
 *  • 6 root items have sub-menus  (3–10 children each)
 *  • 2 of those sub-menus have a 3rd level (inner sub-menus):
 *      1) Technology  →  Sub-topics  →  Courses (5 inner items)
 *      2) Sports      →  Disciplines →  Events  (5 inner items)
 *
 *  RTDB path:  /menu_items/<id>
 *
 *  "subMenuIds" is stored as { "<childId>": true, … }
 *  because plain arrays are unreliable in RTDB.
 *
 *  Usage
 *  -----
 *  npm install firebase-admin
 *  # place your service-account JSON next to this file:
 *  # cp ~/Downloads/serviceAccountKey.json .
 *  node seed_rtdb.js
 * ============================================================
 */

const admin = require("firebase-admin");
const sa    = require("./serviceAccountKey.json");

admin.initializeApp({
  credential:   admin.credential.cert(sa),
  databaseURL: `https://${sa.project_id}-default-rtdb.firebaseio.com`,
});

const db  = admin.database();
const REF = db.ref("menu_items");

// ─── icon helper (free Flaticon PNGs) ────────────────────────────────────────
const I = {
  news:       "https://cdn-icons-png.flaticon.com/512/2965/2965879.png",
  world:      "https://cdn-icons-png.flaticon.com/512/921/921490.png",
  local:      "https://cdn-icons-png.flaticon.com/512/684/684908.png",
  breaking:   "https://cdn-icons-png.flaticon.com/512/1041/1041916.png",
  politics:   "https://cdn-icons-png.flaticon.com/512/3135/3135789.png",
  opinion:    "https://cdn-icons-png.flaticon.com/512/1041/1041884.png",

  events:     "https://cdn-icons-png.flaticon.com/512/2693/2693507.png",
  concert:    "https://cdn-icons-png.flaticon.com/512/3601/3601640.png",
  festival:   "https://cdn-icons-png.flaticon.com/512/3132/3132086.png",
  conference: "https://cdn-icons-png.flaticon.com/512/1705/1705312.png",
  workshop:   "https://cdn-icons-png.flaticon.com/512/2490/2490396.png",
  exhibition: "https://cdn-icons-png.flaticon.com/512/3940/3940406.png",
  charity:    "https://cdn-icons-png.flaticon.com/512/2917/2917995.png",
  online_ev:  "https://cdn-icons-png.flaticon.com/512/2103/2103633.png",

  travel:     "https://cdn-icons-png.flaticon.com/512/201/201623.png",
  beach:      "https://cdn-icons-png.flaticon.com/512/1640/1640012.png",
  mountain:   "https://cdn-icons-png.flaticon.com/512/3240/3240638.png",
  city:       "https://cdn-icons-png.flaticon.com/512/602/602175.png",
  cruise:     "https://cdn-icons-png.flaticon.com/512/870/870177.png",
  safari:     "https://cdn-icons-png.flaticon.com/512/2822/2822386.png",
  camping:    "https://cdn-icons-png.flaticon.com/512/2516/2516990.png",
  budget:     "https://cdn-icons-png.flaticon.com/512/1995/1995567.png",

  health:     "https://cdn-icons-png.flaticon.com/512/2966/2966327.png",
  nutrition:  "https://cdn-icons-png.flaticon.com/512/1046/1046784.png",
  mental:     "https://cdn-icons-png.flaticon.com/512/2966/2966486.png",
  fitness:    "https://cdn-icons-png.flaticon.com/512/2964/2964541.png",
  medicine:   "https://cdn-icons-png.flaticon.com/512/2966/2966401.png",

  education:  "https://cdn-icons-png.flaticon.com/512/2436/2436882.png",
  courses:    "https://cdn-icons-png.flaticon.com/512/1995/1995574.png",
  resources:  "https://cdn-icons-png.flaticon.com/512/2232/2232688.png",
  language:   "https://cdn-icons-png.flaticon.com/512/484/484633.png",
  certif:     "https://cdn-icons-png.flaticon.com/512/3408/3408340.png",

  tech:       "https://cdn-icons-png.flaticon.com/512/2103/2103633.png",
  frontend:   "https://cdn-icons-png.flaticon.com/512/1336/1336494.png",
  backend:    "https://cdn-icons-png.flaticon.com/512/919/919853.png",
  mobile:     "https://cdn-icons-png.flaticon.com/512/0/191.png",
  devops:     "https://cdn-icons-png.flaticon.com/512/4248/4248443.png",
  aiml:       "https://cdn-icons-png.flaticon.com/512/2103/2103652.png",
  security:   "https://cdn-icons-png.flaticon.com/512/2092/2092663.png",
  blockchain: "https://cdn-icons-png.flaticon.com/512/2521/2521751.png",
  iot:        "https://cdn-icons-png.flaticon.com/512/3659/3659899.png",

  science:    "https://cdn-icons-png.flaticon.com/512/3004/3004613.png",
  env:        "https://cdn-icons-png.flaticon.com/512/2920/2920270.png",
  food:       "https://cdn-icons-png.flaticon.com/512/1046/1046784.png",
  culture:    "https://cdn-icons-png.flaticon.com/512/2306/2306154.png",
  finance:    "https://cdn-icons-png.flaticon.com/512/2376/2376432.png",

  sports:     "https://cdn-icons-png.flaticon.com/512/857/857418.png",
  football:   "https://cdn-icons-png.flaticon.com/512/857/857441.png",
  basketball: "https://cdn-icons-png.flaticon.com/512/857/857474.png",
  tennis:     "https://cdn-icons-png.flaticon.com/512/1158/1158148.png",
  cricket:    "https://cdn-icons-png.flaticon.com/512/2972/2972353.png",
  marathon:   "https://cdn-icons-png.flaticon.com/512/857/857455.png",
  cycling:    "https://cdn-icons-png.flaticon.com/512/2972/2972185.png",
  swimming:   "https://cdn-icons-png.flaticon.com/512/2964/2964514.png",
  gym:        "https://cdn-icons-png.flaticon.com/512/2964/2964541.png",

  music:      "https://cdn-icons-png.flaticon.com/512/3659/3659784.png",
  movies:     "https://cdn-icons-png.flaticon.com/512/3073/3073665.png",
  fashion:    "https://cdn-icons-png.flaticon.com/512/2316/2316916.png",
  about:      "https://cdn-icons-png.flaticon.com/512/1144/1144760.png",
};

// ─── item builder ────────────────────────────────────────────────────────────
function node(o) {
  return {
    name:                o.name,
    iconUrl:             o.iconUrl,
    order:               o.order,
    parentId:            o.parentId  ?? null,
    subMenuIds:          o.subMenuIds
                           ? Object.fromEntries(o.subMenuIds.map(id => [id, true]))
                           : {},
    text:                o.text               ?? "",
    youtubeUrl:          o.youtubeUrl          ?? "",
    imageUrl:            o.imageUrl            ?? "",
    primaryButtonText:   o.primaryButtonText   ?? "",
    primaryButtonAction: o.primaryButtonAction ?? "",
  };
}

// ─────────────────────────────────────────────────────────────────────────────
//  LEVEL 3 — inner sub-menus (children of a sub-menu)
// ─────────────────────────────────────────────────────────────────────────────

// Technology > Sub-topics > Courses  (5 items, parentId = "tech_courses")
const techCoursesChildren = {
  "tech_c_frontend": node({
    name:"Frontend Development", iconUrl:I.frontend, order:1, parentId:"tech_courses",
    text:"Master HTML, CSS, JavaScript, React and modern frontend frameworks. Build beautiful, responsive UIs from the ground up.",
    youtubeUrl:"https://www.youtube.com/watch?v=ysEN5RaKOlA",
    imageUrl:"https://images.unsplash.com/photo-1547658719-da2b51169166?w=800",
    primaryButtonText:"Start Learning", primaryButtonAction:"https://www.freecodecamp.org",
  }),
  "tech_c_backend": node({
    name:"Backend Development", iconUrl:I.backend, order:2, parentId:"tech_courses",
    text:"Learn Node.js, Python, databases, REST & GraphQL APIs and server-side architecture to power modern applications.",
    youtubeUrl:"https://www.youtube.com/watch?v=ENrzD9HAZK4",
    imageUrl:"https://images.unsplash.com/photo-1544256718-3bcf237f3974?w=800",
    primaryButtonText:"Start Learning", primaryButtonAction:"https://www.coursera.org/learn/server-side-nodejs",
  }),
  "tech_c_mobile": node({
    name:"Mobile Development", iconUrl:I.mobile, order:3, parentId:"tech_courses",
    text:"Build native and cross-platform mobile apps using Android (Kotlin/Compose), iOS (Swift) and Flutter.",
    youtubeUrl:"https://www.youtube.com/watch?v=F9UC9DY-vIU",
    imageUrl:"https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800",
    primaryButtonText:"Start Learning", primaryButtonAction:"https://developer.android.com/courses",
  }),
  "tech_c_devops": node({
    name:"DevOps & Cloud", iconUrl:I.devops, order:4, parentId:"tech_courses",
    text:"Understand CI/CD, Docker, Kubernetes, AWS and GCP to ship software faster and more reliably.",
    youtubeUrl:"https://www.youtube.com/watch?v=j5Zsa_eOXeY",
    imageUrl:"https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=800",
    primaryButtonText:"Start Learning", primaryButtonAction:"https://www.udemy.com/topic/devops",
  }),
  "tech_c_aiml": node({
    name:"AI & Machine Learning", iconUrl:I.aiml, order:5, parentId:"tech_courses",
    text:"Dive into ML, deep learning and neural networks with Python, TensorFlow and PyTorch through hands-on projects.",
    youtubeUrl:"https://www.youtube.com/watch?v=GwIo3gDZCVQ",
    imageUrl:"https://images.unsplash.com/photo-1677442135703-1787eea5ce01?w=800",
    primaryButtonText:"Start Learning", primaryButtonAction:"https://www.fast.ai",
  }),
};

// Sports > Disciplines > Events (5 items, parentId = "sports_events")
const sportsEventChildren = {
  "sp_ev_football": node({
    name:"Football Matches", iconUrl:I.football, order:1, parentId:"sports_events",
    text:"Upcoming international and club football matches. Get fixtures, ticket info and live streaming links for every game.",
    youtubeUrl:"https://www.youtube.com/watch?v=DJJFn-UVXMU",
    imageUrl:"https://images.unsplash.com/photo-1571008887538-b36bb32f4571?w=800",
    primaryButtonText:"Buy Tickets", primaryButtonAction:"https://www.fifa.com/tickets",
  }),
  "sp_ev_basketball": node({
    name:"Basketball Games", iconUrl:I.basketball, order:2, parentId:"sports_events",
    text:"NBA, EuroLeague and local basketball fixtures. Find venues, timings and broadcast information for upcoming games.",
    youtubeUrl:"https://www.youtube.com/watch?v=hiZwaQ_XULU",
    imageUrl:"https://images.unsplash.com/photo-1546519638-68e109498ffc?w=800",
    primaryButtonText:"View Schedule", primaryButtonAction:"https://www.nba.com/schedule",
  }),
  "sp_ev_tennis": node({
    name:"Tennis Tournaments", iconUrl:I.tennis, order:3, parentId:"sports_events",
    text:"Grand Slams, ATP and WTA tour events — schedules, draws and ticket information for tennis fans worldwide.",
    youtubeUrl:"https://www.youtube.com/watch?v=7uG7iGBjIlw",
    imageUrl:"https://images.unsplash.com/photo-1554068865-24cecd4e34b8?w=800",
    primaryButtonText:"View Schedule", primaryButtonAction:"https://www.atptour.com",
  }),
  "sp_ev_cricket": node({
    name:"Cricket Series", iconUrl:I.cricket, order:4, parentId:"sports_events",
    text:"International Test series, ODIs and T20Is — complete fixtures, venues and live score links for cricket fans.",
    youtubeUrl:"https://www.youtube.com/watch?v=L2yAP6R4wEk",
    imageUrl:"https://images.unsplash.com/photo-1531415074968-036ba1b575da?w=800",
    primaryButtonText:"View Schedule", primaryButtonAction:"https://www.icc-cricket.com/fixtures",
  }),
  "sp_ev_marathon": node({
    name:"Marathon Races", iconUrl:I.marathon, order:5, parentId:"sports_events",
    text:"World major marathons — Berlin, London, Tokyo, New York, Chicago and Boston. Training tips, routes and registration.",
    youtubeUrl:"https://www.youtube.com/watch?v=HdNb7xXFyg4",
    imageUrl:"https://images.unsplash.com/photo-1542751371-adc38448a05e?w=800",
    primaryButtonText:"Register Now", primaryButtonAction:"https://worldathletics.org/competition/calendar",
  }),
};

// ─────────────────────────────────────────────────────────────────────────────
//  LEVEL 2 — sub-menu items (children of root)
// ─────────────────────────────────────────────────────────────────────────────

const level2 = {

  // ── News children ──────────────────────────────────────────────────────────
  "news_world": node({
    name:"World News", iconUrl:I.world, order:1, parentId:"root_news",
    text:"Global headlines from every continent. Stay connected to international events shaping our world today.",
    youtubeUrl:"https://www.youtube.com/watch?v=3gK_2XDpKr4",
    imageUrl:"https://images.unsplash.com/photo-1495020689067-958852a7765e?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://www.bbc.com/news/world",
  }),
  "news_local": node({
    name:"Local News", iconUrl:I.local, order:2, parentId:"root_news",
    text:"Breaking news, community updates and stories from your city and surrounding region.",
    imageUrl:"https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://news.google.com",
  }),
  "news_breaking": node({
    name:"Breaking News", iconUrl:I.breaking, order:3, parentId:"root_news",
    text:"Live breaking news as it happens. Refresh for the latest updates on major stories around the globe.",
    youtubeUrl:"https://www.youtube.com/watch?v=3gK_2XDpKr4",
    primaryButtonText:"Follow Live", primaryButtonAction:"https://www.reuters.com",
  }),
  "news_politics": node({
    name:"Politics", iconUrl:I.politics, order:4, parentId:"root_news",
    text:"In-depth political analysis, election coverage, government policy updates and opinion pieces from expert journalists.",
    imageUrl:"https://images.unsplash.com/photo-1529107386315-e1a2ed48a620?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://www.politico.com",
  }),
  "news_opinion": node({
    name:"Opinion & Editorial", iconUrl:I.opinion, order:5, parentId:"root_news",
    text:"Thoughtful opinion pieces and editorial commentary on the issues that matter most to society.",
    imageUrl:"https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://www.theguardian.com/commentisfree",
  }),

  // ── Events children ────────────────────────────────────────────────────────
  "ev_concert": node({
    name:"Concerts", iconUrl:I.concert, order:1, parentId:"root_events",
    text:"Upcoming live music concerts from top artists worldwide. Find tickets, venues and set-list previews.",
    youtubeUrl:"https://www.youtube.com/watch?v=SlPhMPnQ58k",
    imageUrl:"https://images.unsplash.com/photo-1501281668745-f7f57925c3b4?w=800",
    primaryButtonText:"Get Tickets", primaryButtonAction:"https://www.ticketmaster.com",
  }),
  "ev_festival": node({
    name:"Festivals", iconUrl:I.festival, order:2, parentId:"root_events",
    text:"Art, music, food and cultural festivals happening near you and around the world this season.",
    imageUrl:"https://images.unsplash.com/photo-1533174072545-7a4b6ad7a6c3?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.eventbrite.com",
  }),
  "ev_conference": node({
    name:"Conferences", iconUrl:I.conference, order:3, parentId:"root_events",
    text:"Professional conferences, summits and industry gatherings. Networking opportunities and keynote speakers.",
    imageUrl:"https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=800",
    primaryButtonText:"Register", primaryButtonAction:"https://www.eventbrite.com/d/online/conference",
  }),
  "ev_workshop": node({
    name:"Workshops", iconUrl:I.workshop, order:4, parentId:"root_events",
    text:"Hands-on workshops and masterclasses across technology, art, business and personal development.",
    imageUrl:"https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=800",
    primaryButtonText:"Sign Up", primaryButtonAction:"https://www.meetup.com",
  }),
  "ev_exhibition": node({
    name:"Exhibitions", iconUrl:I.exhibition, order:5, parentId:"root_events",
    text:"Art, photography, science and trade exhibitions from galleries and museums around the world.",
    imageUrl:"https://images.unsplash.com/photo-1531685250784-7569952593d2?w=800",
    primaryButtonText:"Book Visit", primaryButtonAction:"https://www.timeout.com/art",
  }),
  "ev_charity": node({
    name:"Charity Events", iconUrl:I.charity, order:6, parentId:"root_events",
    text:"Make a difference. Charity runs, galas, auctions and fundraising drives that need your support.",
    imageUrl:"https://images.unsplash.com/photo-1532629345422-7515f3d16bb6?w=800",
    primaryButtonText:"Donate", primaryButtonAction:"https://www.charitynavigator.org",
  }),

  // ── Travel children ────────────────────────────────────────────────────────
  "travel_beach": node({
    name:"Beach Getaways", iconUrl:I.beach, order:1, parentId:"root_travel",
    text:"Sun, sand and sea — curated beach destinations from the Maldives to the Caribbean. Book your perfect escape.",
    youtubeUrl:"https://www.youtube.com/watch?v=yMqP8Mm3jHs",
    imageUrl:"https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800",
    primaryButtonText:"Book Now", primaryButtonAction:"https://www.booking.com/beach",
  }),
  "travel_mountain": node({
    name:"Mountain Retreats", iconUrl:I.mountain, order:2, parentId:"root_travel",
    text:"Breathtaking alpine adventures — hiking, skiing and scenic retreats in the world's greatest mountain ranges.",
    youtubeUrl:"https://www.youtube.com/watch?v=7D-cHMTm_5M",
    imageUrl:"https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.alltrails.com",
  }),
  "travel_city": node({
    name:"City Breaks", iconUrl:I.city, order:3, parentId:"root_travel",
    text:"Weekend city breaks in the world's most exciting urban destinations. Culture, cuisine and architecture await.",
    imageUrl:"https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?w=800",
    primaryButtonText:"Plan Trip", primaryButtonAction:"https://www.tripadvisor.com",
  }),
  "travel_budget": node({
    name:"Budget Travel", iconUrl:I.budget, order:4, parentId:"root_travel",
    text:"Travel the world without breaking the bank. Expert tips on cheap flights, hostels and free activities.",
    imageUrl:"https://images.unsplash.com/photo-1488646953014-85cb44e25828?w=800",
    primaryButtonText:"Find Deals", primaryButtonAction:"https://www.skyscanner.com",
  }),

  // ── Technology children (tech_courses has its own level-3 children) ────────
  "tech_latest": node({
    name:"Latest Tech", iconUrl:I.tech, order:1, parentId:"root_technology",
    text:"The hottest new gadgets, software releases and tech industry news. Stay ahead of the curve.",
    youtubeUrl:"https://www.youtube.com/watch?v=Lk2-UpMFIaY",
    imageUrl:"https://images.unsplash.com/photo-1518770660439-4636190af475?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://www.theverge.com",
  }),
  "tech_courses": node({
    name:"Tech Courses", iconUrl:I.courses, order:2, parentId:"root_technology",
    // ← has level-3 children defined above
    subMenuIds: Object.keys(techCoursesChildren),
  }),
  "tech_security": node({
    name:"Cybersecurity", iconUrl:I.security, order:3, parentId:"root_technology",
    text:"Stay safe online. Latest cybersecurity news, vulnerability alerts and best practices for individuals and businesses.",
    youtubeUrl:"https://www.youtube.com/watch?v=inWWhr5tnEA",
    imageUrl:"https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800",
    primaryButtonText:"Learn More", primaryButtonAction:"https://www.cybersecurity.gov",
  }),
  "tech_blockchain": node({
    name:"Blockchain & Web3", iconUrl:I.blockchain, order:4, parentId:"root_technology",
    text:"Decentralised finance, NFTs, smart contracts and the evolving Web3 ecosystem explained simply.",
    imageUrl:"https://images.unsplash.com/photo-1639762681485-074b7f938ba0?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.coindesk.com",
  }),
  "tech_iot": node({
    name:"IoT & Smart Home", iconUrl:I.iot, order:5, parentId:"root_technology",
    text:"Smart devices, home automation, wearables and the Internet of Things — news, reviews and setup guides.",
    imageUrl:"https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.iotforall.com",
  }),

  // ── Sports children (sports_events has its own level-3 children) ──────────
  "sports_news_sub": node({
    name:"Sports News", iconUrl:I.sports, order:1, parentId:"root_sports",
    text:"Latest transfers, injury updates, match previews and post-match analysis across all major sports.",
    imageUrl:"https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://www.espn.com",
  }),
  "sports_events": node({
    name:"Sports Events", iconUrl:I.football, order:2, parentId:"root_sports",
    // ← has level-3 children defined above
    subMenuIds: Object.keys(sportsEventChildren),
  }),
  "sports_fitness": node({
    name:"Fitness Tips", iconUrl:I.gym, order:3, parentId:"root_sports",
    text:"Workout plans, nutrition advice, recovery techniques and motivational content for every fitness level.",
    youtubeUrl:"https://www.youtube.com/watch?v=vc1E5CfRfos",
    imageUrl:"https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=800",
    primaryButtonText:"Get Fit", primaryButtonAction:"https://www.myfitnesspal.com",
  }),
  "sports_results": node({
    name:"Results & Stats", iconUrl:I.basketball, order:4, parentId:"root_sports",
    text:"Live scores, historical results, league tables and player statistics for football, basketball, cricket and more.",
    primaryButtonText:"View Stats", primaryButtonAction:"https://www.flashscore.com",
  }),
  "sports_fantasy": node({
    name:"Fantasy Sports", iconUrl:I.trophy ?? I.sports, order:5, parentId:"root_sports",
    text:"Build your dream team and compete with friends in fantasy football, cricket and basketball leagues.",
    imageUrl:"https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=800",
    primaryButtonText:"Play Now", primaryButtonAction:"https://fantasy.premierleague.com",
  }),
};

// ─────────────────────────────────────────────────────────────────────────────
//  LEVEL 1 — 20 root menu items
// ─────────────────────────────────────────────────────────────────────────────
const level1 = {

  // 1 — has 5 sub-menus
  "root_news": node({
    name:"News", iconUrl:I.news, order:1,
    subMenuIds:["news_world","news_local","news_breaking","news_politics","news_opinion"],
  }),

  // 2 — has 6 sub-menus
  "root_events": node({
    name:"Events", iconUrl:I.events, order:2,
    subMenuIds:["ev_concert","ev_festival","ev_conference","ev_workshop","ev_exhibition","ev_charity"],
  }),

  // 3 — has 4 sub-menus
  "root_travel": node({
    name:"Travel", iconUrl:I.travel, order:3,
    subMenuIds:["travel_beach","travel_mountain","travel_city","travel_budget"],
  }),

  // 4 — leaf item (no submenu)
  "root_health": node({
    name:"Health & Wellness", iconUrl:I.health, order:4,
    text:"Expert advice on physical health, mental wellness, nutrition and preventive medicine to help you live your best life.",
    youtubeUrl:"https://www.youtube.com/watch?v=Hpfx0tC-esU",
    imageUrl:"https://images.unsplash.com/photo-1505751172876-fa1923c5c528?w=800",
    primaryButtonText:"Learn More", primaryButtonAction:"https://www.who.int/health-topics",
  }),

  // 5 — has 5 sub-menus (tech_courses → level 3)
  "root_technology": node({
    name:"Technology", iconUrl:I.tech, order:5,
    subMenuIds:["tech_latest","tech_courses","tech_security","tech_blockchain","tech_iot"],
  }),

  // 6 — leaf
  "root_science": node({
    name:"Science", iconUrl:I.science, order:6,
    text:"Discoveries, breakthroughs and research from space exploration, biology, physics, chemistry and earth sciences.",
    youtubeUrl:"https://www.youtube.com/watch?v=MX3Pi5haWFY",
    imageUrl:"https://images.unsplash.com/photo-1507413245164-6160d8298b31?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.nature.com",
  }),

  // 7 — leaf
  "root_environment": node({
    name:"Environment", iconUrl:I.env, order:7,
    text:"Climate change, sustainability, conservation efforts and green living tips to protect our planet for future generations.",
    youtubeUrl:"https://www.youtube.com/watch?v=G4H1N_yXBiA",
    imageUrl:"https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=800",
    primaryButtonText:"Take Action", primaryButtonAction:"https://www.unep.org",
  }),

  // 8 — leaf
  "root_food": node({
    name:"Food & Recipes", iconUrl:I.food, order:8,
    text:"Delicious recipes, restaurant reviews, cooking techniques and food culture from around the world.",
    youtubeUrl:"https://www.youtube.com/watch?v=pM4E8pBCpJo",
    imageUrl:"https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800",
    primaryButtonText:"Browse Recipes", primaryButtonAction:"https://www.allrecipes.com",
  }),

  // 9 — leaf
  "root_culture": node({
    name:"Culture & Arts", iconUrl:I.culture, order:9,
    text:"Art, literature, history, philosophy and the cultural movements shaping our collective identity.",
    imageUrl:"https://images.unsplash.com/photo-1578301978693-85fa9c0320b9?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.theartnewspaper.com",
  }),

  // 10 — leaf
  "root_finance": node({
    name:"Finance", iconUrl:I.finance, order:10,
    text:"Personal finance tips, stock market updates, investment strategies and economic news for smart money management.",
    youtubeUrl:"https://www.youtube.com/watch?v=HQzoZfc3GwQ",
    imageUrl:"https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800",
    primaryButtonText:"Learn More", primaryButtonAction:"https://www.investopedia.com",
  }),

  // 11 — has 5 sub-menus (sports_events → level 3)
  "root_sports": node({
    name:"Sports", iconUrl:I.sports, order:11,
    subMenuIds:["sports_news_sub","sports_events","sports_fitness","sports_results","sports_fantasy"],
  }),

  // 12 — leaf
  "root_music": node({
    name:"Music", iconUrl:I.music, order:12,
    text:"New releases, album reviews, artist spotlights, music history and concert guides for every genre.",
    youtubeUrl:"https://www.youtube.com/watch?v=SlPhMPnQ58k",
    imageUrl:"https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=800",
    primaryButtonText:"Listen Now", primaryButtonAction:"https://open.spotify.com",
  }),

  // 13 — leaf
  "root_movies": node({
    name:"Movies & TV", iconUrl:I.movies, order:13,
    text:"Box office news, TV show recaps, trailers, reviews and streaming recommendations for every taste.",
    youtubeUrl:"https://www.youtube.com/watch?v=3gK_2XDpKr4",
    imageUrl:"https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=800",
    primaryButtonText:"Browse", primaryButtonAction:"https://www.imdb.com",
  }),

  // 14 — leaf
  "root_fashion": node({
    name:"Fashion", iconUrl:I.fashion, order:14,
    text:"Fashion weeks, trend reports, style guides and sustainable fashion news from leading designers and brands.",
    imageUrl:"https://images.unsplash.com/photo-1445205170230-053b83016050?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.vogue.com",
  }),

  // 15 — leaf
  "root_education": node({
    name:"Education", iconUrl:I.education, order:15,
    text:"Learning resources, scholarship opportunities, education policy updates and tips for students of all ages.",
    youtubeUrl:"https://www.youtube.com/watch?v=V75dMMIW2B4",
    imageUrl:"https://images.unsplash.com/photo-1509062522246-3755977927d7?w=800",
    primaryButtonText:"Start Learning", primaryButtonAction:"https://www.khanacademy.org",
  }),

  // 16 — leaf
  "root_business": node({
    name:"Business", iconUrl:I.finance, order:16,
    text:"Entrepreneurship, startups, corporate news, management strategies and business insights for leaders.",
    imageUrl:"https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=800",
    primaryButtonText:"Read More", primaryButtonAction:"https://www.hbr.org",
  }),

  // 17 — leaf
  "root_photography": node({
    name:"Photography", iconUrl:I.exhibitions ?? I.exhibition, order:17,
    text:"Photography tutorials, gear reviews, editing techniques and inspiration galleries for photographers at every level.",
    youtubeUrl:"https://www.youtube.com/watch?v=LxO-6rlihSg",
    imageUrl:"https://images.unsplash.com/photo-1502982720700-bfff97f2ecac?w=800",
    primaryButtonText:"Get Inspired", primaryButtonAction:"https://www.500px.com",
  }),

  // 18 — leaf
  "root_gaming": node({
    name:"Gaming", iconUrl:I.tech, order:18,
    text:"Game reviews, esports coverage, release dates, tips and tricks and hardware recommendations for all platforms.",
    youtubeUrl:"https://www.youtube.com/watch?v=rQgg6NM_MNc",
    imageUrl:"https://images.unsplash.com/photo-1542751371-adc38448a05e?w=800",
    primaryButtonText:"Explore", primaryButtonAction:"https://www.ign.com",
  }),

  // 19 — leaf
  "root_weather": node({
    name:"Weather", iconUrl:I.env, order:19,
    text:"Live weather forecasts, storm alerts, climate data and seasonal outlooks for locations around the world.",
    primaryButtonText:"Check Weather", primaryButtonAction:"https://www.weather.com",
  }),

  // 20 — leaf
  "root_about": node({
    name:"About Us", iconUrl:I.about, order:20,
    text:"We are a passionate team delivering quality information to our users every day. Our mission: keep you informed, inspired, and empowered.\n\nFounded in 2024, we have served over 100,000 users across 50 countries.",
    youtubeUrl:"https://www.youtube.com/watch?v=dQw4w9WgXcQ",
    imageUrl:"https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=800",
    primaryButtonText:"Visit Website", primaryButtonAction:"https://example.com/about",
  }),
};

// ─────────────────────────────────────────────────────────────────────────────
//  WRITE TO RTDB
// ─────────────────────────────────────────────────────────────────────────────
async function seed() {
  const all = {
    ...level1,
    ...level2,
    ...techCoursesChildren,
    ...sportsEventChildren,
  };

  console.log(`\n🌱  Seeding ${Object.keys(all).length} nodes to /menu_items …\n`);

  await REF.set(all);

  // Print summary
  const roots  = Object.keys(level1).length;
  const subs   = Object.keys(level2).length;
  const inner  = Object.keys(techCoursesChildren).length + Object.keys(sportsEventChildren).length;

  console.log("✅  Done!\n");
  console.log(`   Level 1 (root)    : ${roots} items`);
  console.log(`   Level 2 (sub)     : ${subs} items`);
  console.log(`   Level 3 (inner)   : ${inner} items`);
  console.log(`   Total             : ${roots + subs + inner} items\n`);
  console.log("Tree preview:");
  console.log(`
  Dashboard (20 root items)
  ├── 📰 News            → 5 sub-items  (World, Local, Breaking, Politics, Opinion)
  ├── 🎭 Events          → 6 sub-items  (Concerts, Festivals, Conferences, …)
  ├── ✈️  Travel          → 4 sub-items  (Beach, Mountain, City, Budget)
  ├── 💚 Health          → (content)
  ├── 💻 Technology      → 5 sub-items
  │       └── Tech Courses → 5 inner items  ⬅ Level 3
  │               (Frontend, Backend, Mobile, DevOps, AI/ML)
  ├── 🔬 Science         → (content)
  ├── 🌿 Environment     → (content)
  ├── 🍔 Food & Recipes  → (content)
  ├── 🎨 Culture & Arts  → (content)
  ├── 💰 Finance         → (content)
  ├── 🏅 Sports          → 5 sub-items
  │       └── Sports Events → 5 inner items  ⬅ Level 3
  │               (Football, Basketball, Tennis, Cricket, Marathon)
  ├── 🎵 Music           → (content)
  ├── 🎬 Movies & TV     → (content)
  ├── 👗 Fashion         → (content)
  ├── 📚 Education       → (content)
  ├── 💼 Business        → (content)
  ├── 📸 Photography     → (content)
  ├── 🎮 Gaming          → (content)
  ├── 🌤  Weather        → (content)
  └── ℹ️  About Us       → (content)
`);

  process.exit(0);
}

seed().catch(err => {
  console.error("❌  Seed failed:", err.message);
  process.exit(1);
});
