package com.infoapp.tools

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.infoapp.core.di.FirebaseModule

object FirebaseSeeder {

    private val db by lazy {
        FirebaseDatabase.getInstance(FirebaseModule.DATABASE_URL)
    }

    private val ref by lazy { db.getReference("menu_items") }


    object GovIcons {
        // Department-level icons
        const val CAR          = "https://cdn-icons-png.flaticon.com/512/741/741407.png"
        const val POLICE       = "https://cdn-icons-png.flaticon.com/512/3064/3064197.png"
        const val HEALTH       = "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"
        const val LAND         = "https://cdn-icons-png.flaticon.com/512/684/684908.png"
        const val DIGITAL      = "https://cdn-icons-png.flaticon.com/512/1006/1006555.png"
        const val FARM         = "https://cdn-icons-png.flaticon.com/512/1995/1995482.png"
        const val LOCAL        = "https://cdn-icons-png.flaticon.com/512/1257/1257208.png"
        const val WATER        = "https://cdn-icons-png.flaticon.com/512/728/728093.png"
        const val ELECTRICITY  = "https://cdn-icons-png.flaticon.com/512/702/702814.png"
        const val EDUCATION    = "https://cdn-icons-png.flaticon.com/512/3135/3135755.png"

        // MVD sub-icons
        const val LICENSE      = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
        const val VEHICLE      = "https://cdn-icons-png.flaticon.com/512/741/741407.png"
        const val TAX          = "https://cdn-icons-png.flaticon.com/512/2331/2331944.png"
        const val FINE         = "https://cdn-icons-png.flaticon.com/512/2519/2519370.png"
        const val PERMIT       = "https://cdn-icons-png.flaticon.com/512/3081/3081559.png"

        // Police sub-icons
        const val FIR          = "https://cdn-icons-png.flaticon.com/512/2991/2991108.png"
        const val MISSING      = "https://cdn-icons-png.flaticon.com/512/3209/3209265.png"
        const val CYBER        = "https://cdn-icons-png.flaticon.com/512/2092/2092757.png"
        const val EMERGENCY    = "https://cdn-icons-png.flaticon.com/512/564/564619.png"
        const val HELP         = "https://cdn-icons-png.flaticon.com/512/4961/4961736.png"

        // Health sub-icons
        const val HOSPITAL     = "https://cdn-icons-png.flaticon.com/512/3063/3063175.png"
        const val VACCINE      = "https://cdn-icons-png.flaticon.com/512/2913/2913134.png"
        const val CALENDAR     = "https://cdn-icons-png.flaticon.com/512/747/747310.png"
        const val RECORDS      = "https://cdn-icons-png.flaticon.com/512/3815/3815523.png"
        const val AMBULANCE    = "https://cdn-icons-png.flaticon.com/512/2966/2966486.png"

        // Revenue sub-icons
        const val CERTIFICATE  = "https://cdn-icons-png.flaticon.com/512/3135/3135679.png"
        const val MUTATION     = "https://cdn-icons-png.flaticon.com/512/2911/2911234.png"
        const val SURVEY       = "https://cdn-icons-png.flaticon.com/512/684/684809.png"

        // Education sub-icons
        const val SCHOLARSHIP  = "https://cdn-icons-png.flaticon.com/512/3135/3135768.png"
        const val ADMISSION    = "https://cdn-icons-png.flaticon.com/512/3135/3135763.png"
        const val RESULTS      = "https://cdn-icons-png.flaticon.com/512/3135/3135795.png"
        const val SCHOOL       = "https://cdn-icons-png.flaticon.com/512/2602/2602414.png"

        // Utility sub-icons
        const val BILL         = "https://cdn-icons-png.flaticon.com/512/1198/1198342.png"
        const val CONNECTION   = "https://cdn-icons-png.flaticon.com/512/1995/1995574.png"
        const val COMPLAINT    = "https://cdn-icons-png.flaticon.com/512/4233/4233839.png"
        const val USAGE        = "https://cdn-icons-png.flaticon.com/512/2620/2620578.png"
        const val METER        = "https://cdn-icons-png.flaticon.com/512/1808/1808673.png"
        const val TANKER       = "https://cdn-icons-png.flaticon.com/512/2933/2933116.png"
        const val QUALITY      = "https://cdn-icons-png.flaticon.com/512/2913/2913992.png"

        // Local Govt sub-icons
        const val BIRTH        = "https://cdn-icons-png.flaticon.com/512/3163/3163494.png"
        const val WELFARE      = "https://cdn-icons-png.flaticon.com/512/3209/3209074.png"
        const val GRIEVANCE    = "https://cdn-icons-png.flaticon.com/512/4961/4961759.png"

        // Agriculture sub-icons
        const val SUBSIDY      = "https://cdn-icons-png.flaticon.com/512/2933/2933245.png"
        const val CROP         = "https://cdn-icons-png.flaticon.com/512/2909/2909808.png"
        const val INSURANCE    = "https://cdn-icons-png.flaticon.com/512/2933/2933189.png"
        const val MARKET       = "https://cdn-icons-png.flaticon.com/512/3081/3081840.png"
        const val SCHEME       = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png"

        // e-Gov sub-icons
        const val DIGITAL_ID   = "https://cdn-icons-png.flaticon.com/512/3094/3094946.png"
        const val SERVICES     = "https://cdn-icons-png.flaticon.com/512/2920/2920277.png"
        const val STATUS       = "https://cdn-icons-png.flaticon.com/512/1828/1828640.png"
        const val SUPPORT      = "https://cdn-icons-png.flaticon.com/512/4961/4961736.png"
    }

    // ─────────────────────────────────────────────────────────────
    // SERVICE IMAGES (Unsplash – free, hotlinkable banners)
    // ─────────────────────────────────────────────────────────────
    private object Banner {
        const val LICENSE     = "https://images.unsplash.com/photo-1449965408869-eaa3f722e40d?w=800"
        const val VEHICLE     = "https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?w=800"
        const val FINE        = "https://images.unsplash.com/photo-1567361808960-dec9cb578182?w=800"
        const val ROAD_TAX    = "https://images.unsplash.com/photo-1554224155-6726b3ff858f?w=800"
        const val PERMIT      = "https://images.unsplash.com/photo-1601584115197-04ecc0da31d7?w=800"

        const val FIR         = "https://images.unsplash.com/photo-1589994965851-a8f479c573a9?w=800"
        const val MISSING     = "https://images.unsplash.com/photo-1521791136064-7986c2920216?w=800"
        const val CYBER       = "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800"
        const val EMERGENCY   = "https://images.unsplash.com/photo-1587745416684-47953f16f02f?w=800"
        const val HELPLINE    = "https://images.unsplash.com/photo-1556761175-5973dc0f32e7?w=800"

        const val HOSPITAL    = "https://images.unsplash.com/photo-1586773860418-d37222d8fce3?w=800"
        const val VACCINE     = "https://images.unsplash.com/photo-1605289982774-9a6fef564df8?w=800"
        const val APPOINTMENT = "https://images.unsplash.com/photo-1576091160550-2173dba999ef?w=800"
        const val RECORDS     = "https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?w=800"
        const val AMBULANCE   = "https://images.unsplash.com/photo-1612531385446-f7e6d131e1d0?w=800"

        const val LAND        = "https://images.unsplash.com/photo-1500382017468-9049fed747ef?w=800"
        const val PROP_TAX    = "https://images.unsplash.com/photo-1560518883-ce09059eeffa?w=800"
        const val CERTIFICATE = "https://images.unsplash.com/photo-1606326608606-aa0b62935f2b?w=800"
        const val MUTATION    = "https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=800"
        const val SURVEY      = "https://images.unsplash.com/photo-1581094794329-c8112a89af12?w=800"

        const val SCHOLARSHIP = "https://images.unsplash.com/photo-1523050854058-8df90110c9f1?w=800"
        const val ADMISSION   = "https://images.unsplash.com/photo-1571260899304-425eee4c7efc?w=800"
        const val RESULTS     = "https://images.unsplash.com/photo-1606326608606-aa0b62935f2b?w=800"
        const val EDU_CERT    = "https://images.unsplash.com/photo-1513258496099-48168024aec0?w=800"
        const val SCHOOL      = "https://images.unsplash.com/photo-1580582932707-520aed937b7b?w=800"

        const val ELEC_BILL   = "https://images.unsplash.com/photo-1473341304170-971dccb5ac1e?w=800"
        const val ELEC_CONN   = "https://images.unsplash.com/photo-1559302995-f1d7e5b14b80?w=800"
        const val USAGE       = "https://images.unsplash.com/photo-1611224885990-ab7363d1f2a9?w=800"
        const val METER       = "https://images.unsplash.com/photo-1521618755572-156ae0cdd74d?w=800"

        const val WATER_BILL  = "https://images.unsplash.com/photo-1548839140-29a749e1cf4d?w=800"
        const val WATER_CONN  = "https://images.unsplash.com/photo-1534274988757-a28bf1a57c17?w=800"
        const val WATER_QLY   = "https://images.unsplash.com/photo-1559825481-12a05cc00344?w=800"
        const val TANKER      = "https://images.unsplash.com/photo-1567789884554-0b844b597180?w=800"

        const val BIRTH       = "https://images.unsplash.com/photo-1519689680058-324335c77eba?w=800"
        const val LICENSES    = "https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=800"
        const val WELFARE     = "https://images.unsplash.com/photo-1488521787991-ed7bbaae773c?w=800"
        const val GRIEVANCE   = "https://images.unsplash.com/photo-1556761175-b413da4baf72?w=800"

        const val SUBSIDY     = "https://images.unsplash.com/photo-1500595046743-cd271d694d30?w=800"
        const val CROP        = "https://images.unsplash.com/photo-1574943320219-553eb213f72d?w=800"
        const val CROP_INS    = "https://images.unsplash.com/photo-1625246333195-78d9c38ad449?w=800"
        const val MARKET      = "https://images.unsplash.com/photo-1488459716781-31db52582fe9?w=800"
        const val SCHEME      = "https://images.unsplash.com/photo-1444653614773-995cb1ef9efa?w=800"

        const val EGOV_CERT   = "https://images.unsplash.com/photo-1554224155-1696413565d3?w=800"
        const val DIGI_ID     = "https://images.unsplash.com/photo-1614064641938-3bbee52942c7?w=800"
        const val EGOV_SERV   = "https://images.unsplash.com/photo-1551434678-e076c223a692?w=800"
        const val APP_STATUS  = "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=800"
        const val SUPPORT     = "https://images.unsplash.com/photo-1556761175-4b46a572b786?w=800"
    }

    // ─────────────────────────────────────────────────────────────
    // DATA MODEL
    // ─────────────────────────────────────────────────────────────
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

    private fun subIds(vararg ids: String) = ids.associateWith { true }

    // ─────────────────────────────────────────────────────────────
    // MOTOR VEHICLES DEPARTMENT (MVD)
    // ─────────────────────────────────────────────────────────────
    private val mvd = mapOf(
        "mvd_license" to MenuNode(
            name = "Driving Licence",
            iconUrl = GovIcons.LICENSE,
            order = 1,
            parentId = "root_mvd",
            text = "Apply for a new Learner's Licence (LLR), book your driving test, " +
                    "renew an existing Driving Licence, add a new vehicle class, change " +
                    "your address, or get a duplicate licence. All applications are " +
                    "processed through the Parivahan Sarathi portal of the Ministry of " +
                    "Road Transport & Highways. Required documents: Aadhaar, age proof, " +
                    "address proof, and a recent photograph. Fees vary by service " +
                    "(₹200 for LLR, ₹200 for DL, ₹400 for renewal).",
            youtubeUrl = "https://www.youtube.com/watch?v=eFp-9bpqpGE",
            imageUrl = Banner.LICENSE,
            primaryButtonText = "Apply on Sarathi",
            primaryButtonAction = "https://sarathi.parivahan.gov.in/sarathiservice/stateSelection.do"
        ),
        "mvd_vehicle" to MenuNode(
            name = "Vehicle Registration",
            iconUrl = GovIcons.VEHICLE,
            order = 2,
            parentId = "root_mvd",
            text = "Register a new vehicle, transfer ownership (RC transfer), change " +
                    "address on RC, apply for a duplicate Registration Certificate, NOC " +
                    "for inter-state transfer, or hypothecation addition/removal. All " +
                    "services are available on the Parivahan Vahan portal. Keep ready: " +
                    "Form 20, sale invoice, insurance, PUC, and address proof.",
            youtubeUrl = "https://www.youtube.com/watch?v=pZ9oUWJ-CAg",
            imageUrl = Banner.VEHICLE,
            primaryButtonText = "Open Vahan Portal",
            primaryButtonAction = "https://vahan.parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml"
        ),
        "mvd_fines" to MenuNode(
            name = "Traffic Fines",
            iconUrl = GovIcons.FINE,
            order = 3,
            parentId = "root_mvd",
            text = "Check and pay e-Challans issued by Kerala Police and MVD for " +
                    "traffic violations such as over-speeding, signal jumping, no-helmet, " +
                    "drunk driving, and parking violations. You can pay using your " +
                    "vehicle number or challan number. Outstanding fines may block " +
                    "RC renewal or fitness certification.",
            youtubeUrl = "https://www.youtube.com/watch?v=ZNa5K5N-w_0",
            imageUrl = Banner.FINE,
            primaryButtonText = "Pay e-Challan",
            primaryButtonAction = "https://echallan.parivahan.gov.in/index/accused-challan"
        ),
        "mvd_tax" to MenuNode(
            name = "Road Tax",
            iconUrl = GovIcons.TAX,
            order = 4,
            parentId = "root_mvd",
            text = "Pay one-time or quarterly road tax for private and commercial " +
                    "vehicles in Kerala. Two-wheelers and cars under 15 years pay " +
                    "lifetime tax (6%–20% of vehicle cost depending on age and price). " +
                    "Commercial vehicles pay quarterly tax based on seating/load capacity. " +
                    "Late payment attracts penalty + interest.",
            youtubeUrl = "https://www.youtube.com/watch?v=vUQTr4Xw0_w",
            imageUrl = Banner.ROAD_TAX,
            primaryButtonText = "Pay Road Tax",
            primaryButtonAction = "https://vahan.parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml"
        ),
        "mvd_permit" to MenuNode(
            name = "Permits",
            iconUrl = GovIcons.PERMIT,
            order = 5,
            parentId = "root_mvd",
            text = "Apply for transport permits required for goods carriers, " +
                    "passenger vehicles, taxis, autorickshaws, contract carriages, and " +
                    "tourist vehicles. Includes State Permit, All-India Tourist Permit, " +
                    "National Permit, and Temporary Permits. Renewal must be done before " +
                    "expiry to avoid penalty.",
            youtubeUrl = "https://www.youtube.com/watch?v=6tTaaG2mYyA",
            imageUrl = Banner.PERMIT,
            primaryButtonText = "Apply for Permit",
            primaryButtonAction = "https://vahan.parivahan.gov.in/vahanservice/vahan/ui/statevalidation/homepage.xhtml"
        ),
    )


    private val police = mapOf(
        "police_fir" to MenuNode(
            name = "File FIR",
            iconUrl = GovIcons.FIR,
            order = 1,
            parentId = "root_police",
            text = "File a First Information Report (FIR) online for cognizable " +
                    "offences such as theft, burglary, missing items, vehicle theft, and " +
                    "minor crimes through the Kerala Police Citizen Portal (Thuna). For " +
                    "serious or violent crimes, please visit the nearest police station " +
                    "in person. You can also track the status of an existing FIR.",
            youtubeUrl = "https://www.youtube.com/watch?v=vdK7iFbRZ8s",
            imageUrl = Banner.FIR,
            primaryButtonText = "File FIR Online",
            primaryButtonAction = "https://thuna.keralapolice.gov.in/"
        ),
        "police_missing" to MenuNode(
            name = "Missing Persons",
            iconUrl = GovIcons.MISSING,
            order = 2,
            parentId = "root_police",
            text = "Report a missing person, search the Kerala Police database for " +
                    "missing persons, and view recovered/identified persons. Include a " +
                    "recent photograph, last-seen location, physical description, and " +
                    "contact details. The faster a report is filed, the higher the " +
                    "chance of recovery.",
            youtubeUrl = "https://www.youtube.com/watch?v=l0d4Z3K2qXM",
            imageUrl = Banner.MISSING,
            primaryButtonText = "Report / Search",
            primaryButtonAction = "https://citizen.keralapolice.gov.in/MissingHome"
        ),
        "police_cyber" to MenuNode(
            name = "Cyber Crime",
            iconUrl = GovIcons.CYBER,
            order = 3,
            parentId = "root_police",
            text = "Report online financial fraud, UPI scams, phishing, social media " +
                    "harassment, identity theft, ransomware, and cyber stalking. For " +
                    "financial fraud, call 1930 within 24 hours to freeze the transaction. " +
                    "Crimes against women and children can be reported anonymously on " +
                    "the National Cyber Crime Reporting Portal.",
            youtubeUrl = "https://www.youtube.com/watch?v=NqKQp-LPjGE",
            imageUrl = Banner.CYBER,
            primaryButtonText = "Report Cyber Crime",
            primaryButtonAction = "https://cybercrime.gov.in/"
        ),
        "police_emergency" to MenuNode(
            name = "Emergency Help",
            iconUrl = GovIcons.EMERGENCY,
            order = 4,
            parentId = "root_police",
            text = "For any police emergency, dial 112 — Kerala's unified emergency " +
                    "response number. The control room dispatches the nearest patrol " +
                    "unit using GPS. Other key numbers: 100 (Police), 101 (Fire), " +
                    "108 (Ambulance), 1098 (Childline), 181 (Women Helpline). " +
                    "Tap the button to dial 112 immediately.",
            youtubeUrl = "https://www.youtube.com/watch?v=zSyrZQ4cGpE",
            imageUrl = Banner.EMERGENCY,
            primaryButtonText = "Call 112 Now",
            primaryButtonAction = "tel:112"
        ),
        "police_help" to MenuNode(
            name = "Helplines",
            iconUrl = GovIcons.HELP,
            order = 5,
            parentId = "root_police",
            text = "List of all important Kerala Police helplines: Pink Patrol (women " +
                    "safety) – 1515, Senior Citizens – 9497900900, Anti-Narcotics – " +
                    "9497927000, Traffic Helpline – 9497900400, Coastal Police – " +
                    "1093, Tourist Police – 9497900111. All lines work 24×7.",
            youtubeUrl = "https://www.youtube.com/watch?v=Ck6L47C9KbM",
            imageUrl = Banner.HELPLINE,
            primaryButtonText = "View All Helplines",
            primaryButtonAction = "https://keralapolice.gov.in/page/helpline-numbers"
        ),
    )


    private val health = mapOf(
        "health_hospitals" to MenuNode(
            name = "Hospitals",
            iconUrl = GovIcons.HOSPITAL,
            order = 1,
            parentId = "root_health",
            text = "Find government hospitals, taluk hospitals, primary health " +
                    "centres (PHC), community health centres (CHC), and family health " +
                    "centres near you. Check available specialities, OP timings, " +
                    "bed availability, and contact numbers. Kerala has one of India's " +
                    "strongest public healthcare networks with hospitals in every panchayat.",
            youtubeUrl = "https://www.youtube.com/watch?v=2g811Eo7K8U",
            imageUrl = Banner.HOSPITAL,
            primaryButtonText = "Find a Hospital",
            primaryButtonAction = "https://dhs.kerala.gov.in/hospitals/"
        ),
        "health_vaccine" to MenuNode(
            name = "Vaccination",
            iconUrl = GovIcons.VACCINE,
            order = 2,
            parentId = "root_health",
            text = "Book vaccination slots for COVID-19 boosters, child immunisation " +
                    "(BCG, OPV, MMR, DPT, Hepatitis-B), HPV, influenza, and travel " +
                    "vaccines. Government vaccination is free at all PHCs and CHCs " +
                    "under the Universal Immunisation Programme. Use CoWIN to download " +
                    "your vaccination certificate.",
            youtubeUrl = "https://www.youtube.com/watch?v=3aZ4Ym7GnBA",
            imageUrl = Banner.VACCINE,
            primaryButtonText = "Book on CoWIN",
            primaryButtonAction = "https://www.cowin.gov.in/"
        ),
        "health_appointments" to MenuNode(
            name = "Appointments",
            iconUrl = GovIcons.CALENDAR,
            order = 3,
            parentId = "root_health",
            text = "Book OP appointments at government hospitals (Medical Colleges, " +
                    "General Hospitals, Taluk Hospitals) through the eHealth Kerala " +
                    "platform. Skip the queue by reserving a specific time slot with " +
                    "your preferred doctor. ABHA-linked appointments allow your medical " +
                    "history to be shared with the consulting doctor.",
            youtubeUrl = "https://www.youtube.com/watch?v=3Y7KqFbX9oY",
            imageUrl = Banner.APPOINTMENT,
            primaryButtonText = "Book Appointment",
            primaryButtonAction = "https://ehealth.kerala.gov.in/"
        ),
        "health_records" to MenuNode(
            name = "Health Records",
            iconUrl = GovIcons.RECORDS,
            order = 4,
            parentId = "root_health",
            text = "Create your Ayushman Bharat Health Account (ABHA) — a 14-digit " +
                    "unique health ID that securely stores prescriptions, lab reports, " +
                    "discharge summaries, and consultation history. Share records with " +
                    "any doctor instantly via QR code. 100% paperless and consent-driven.",
            youtubeUrl = "https://www.youtube.com/watch?v=fW5e0wU-Z1k",
            imageUrl = Banner.RECORDS,
            primaryButtonText = "Create ABHA",
            primaryButtonAction = "https://abha.abdm.gov.in/abha/v3/register"
        ),
        "health_ambulance" to MenuNode(
            name = "Ambulance",
            iconUrl = GovIcons.AMBULANCE,
            order = 5,
            parentId = "root_health",
            text = "Dial 108 for free 24×7 ambulance service across Kerala under the " +
                    "Kanivu 108 scheme. GPS-enabled ambulances with trained EMTs and " +
                    "advanced/basic life support reach the location within 15-20 minutes " +
                    "in cities. The service is completely free for medical, accident, " +
                    "and pregnancy emergencies.",
            youtubeUrl = "https://www.youtube.com/watch?v=qj8oLCzKoMg",
            imageUrl = Banner.AMBULANCE,
            primaryButtonText = "Call 108",
            primaryButtonAction = "tel:108"
        ),
    )


    private val revenue = mapOf(
        "rev_land" to MenuNode(
            name = "Land Records",
            iconUrl = GovIcons.LAND,
            order = 1,
            parentId = "root_revenue",
            text = "View and download land records (Pokkuvaravu / Thandapper) " +
                    "through the e-Rekha and ReLIS portals. Check ownership, survey " +
                    "number, extent, classification (nilam/purayidam), and tax status. " +
                    "Useful for property purchase, loan applications, and legal " +
                    "verification. Records are updated by Village Offices.",
            youtubeUrl = "https://www.youtube.com/watch?v=8cT4-h0nVgE",
            imageUrl = Banner.LAND,
            primaryButtonText = "View Land Records",
            primaryButtonAction = "https://erekha.kerala.gov.in/"
        ),
        "rev_tax" to MenuNode(
            name = "Property Tax",
            iconUrl = GovIcons.TAX,
            order = 2,
            parentId = "root_revenue",
            text = "Pay basic land tax (Bhoo Nikuthi) online via the ReLIS portal. " +
                    "Land tax rates depend on classification — wet land (nilam), garden " +
                    "land (purayidam), commercial, etc. Pay annually to keep records " +
                    "active. The receipt is required for many other revenue services " +
                    "and certificate applications.",
            youtubeUrl = "https://www.youtube.com/watch?v=I9X4YhLB4sY",
            imageUrl = Banner.PROP_TAX,
            primaryButtonText = "Pay Land Tax",
            primaryButtonAction = "https://revenue.kerala.gov.in/"
        ),
        "rev_certificate" to MenuNode(
            name = "Certificates",
            iconUrl = GovIcons.CERTIFICATE,
            order = 3,
            parentId = "root_revenue",
            text = "Apply online for revenue certificates: Income Certificate, " +
                    "Caste Certificate, Domicile/Nativity Certificate, Possession " +
                    "Certificate, Location Certificate, Non-Remarriage Certificate, " +
                    "and Family Membership Certificate. Issued by Tahsildar / Village " +
                    "Officer through the Akshaya/eDistrict portal — typical turnaround " +
                    "is 7-15 days.",
            youtubeUrl = "https://www.youtube.com/watch?v=6FQjJ2RhdEM",
            imageUrl = Banner.CERTIFICATE,
            primaryButtonText = "Apply on eDistrict",
            primaryButtonAction = "https://edistrict.kerala.gov.in/"
        ),
        "rev_mutation" to MenuNode(
            name = "Mutation (Pokkuvaravu)",
            iconUrl = GovIcons.MUTATION,
            order = 4,
            parentId = "root_revenue",
            text = "Apply for mutation of land records (Pokkuvaravu) when ownership " +
                    "changes due to sale, gift, partition, inheritance, or court order. " +
                    "After mutation, the Thandapper is updated to reflect the new owner. " +
                    "Required documents: title deed, encumbrance certificate, latest " +
                    "tax receipt, and identity proof.",
            youtubeUrl = "https://www.youtube.com/watch?v=oXq7bKXt_pE",
            imageUrl = Banner.MUTATION,
            primaryButtonText = "Apply for Mutation",
            primaryButtonAction = "https://revenue.kerala.gov.in/"
        ),
        "rev_search" to MenuNode(
            name = "Survey Search",
            iconUrl = GovIcons.SURVEY,
            order = 5,
            parentId = "root_revenue",
            text = "Search and view cadastral survey maps, sub-division details, " +
                    "and re-survey numbers through the e-Rekha portal of the Kerala " +
                    "Survey Department. View the boundary, neighbours, and extent of " +
                    "any plot. Useful for property buyers, developers, and lawyers " +
                    "verifying boundaries.",
            youtubeUrl = "https://www.youtube.com/watch?v=lQYbR-tF9_Y",
            imageUrl = Banner.SURVEY,
            primaryButtonText = "Open e-Rekha",
            primaryButtonAction = "https://erekha.kerala.gov.in/"
        ),
    )


    private val education = mapOf(
        "edu_scholarship" to MenuNode(
            name = "Scholarships",
            iconUrl = GovIcons.SCHOLARSHIP,
            order = 1,
            parentId = "root_education",
            text = "Apply for state and central scholarships through the e-Grantz " +
                    "(Kerala) and NSP (National) portals. Schemes include post-matric " +
                    "scholarships for SC/ST/OBC students, minority scholarships, " +
                    "merit-cum-means awards, Mukhyamantri scholarships, and CM Scholarship " +
                    "for higher education. Renewal is required every academic year.",
            youtubeUrl = "https://www.youtube.com/watch?v=rNlZbxgAkWE",
            imageUrl = Banner.SCHOLARSHIP,
            primaryButtonText = "Apply on e-Grantz",
            primaryButtonAction = "https://egrantz.kerala.gov.in/"
        ),
        "edu_admission" to MenuNode(
            name = "Admissions",
            iconUrl = GovIcons.ADMISSION,
            order = 2,
            parentId = "root_education",
            text = "Online admission for Plus One (HSCAP), Degree (KEAM, CAP), " +
                    "Engineering & Medical (KEAM), Polytechnic, and ITI courses. Each " +
                    "stream has its own portal with separate cut-off dates. Track " +
                    "allotments, accept seat, and pay fees online. Reservation rules " +
                    "apply as per Kerala Government policy.",
            youtubeUrl = "https://www.youtube.com/watch?v=z5xqNi9fqPI",
            imageUrl = Banner.ADMISSION,
            primaryButtonText = "Plus One Admission",
            primaryButtonAction = "https://hscap.kerala.gov.in/"
        ),
        "edu_results" to MenuNode(
            name = "Exam Results",
            iconUrl = GovIcons.RESULTS,
            order = 3,
            parentId = "root_education",
            text = "Check results for SSLC (Class 10), Plus Two (HSE), VHSE, " +
                    "KEAM, Degree, PG, and Open School exams. Official portal: " +
                    "results.itschool.gov.in / keralaresults.nic.in. Save your " +
                    "register number, school code, and date of birth handy. " +
                    "Mark-list copies can be downloaded after publication.",
            youtubeUrl = "https://www.youtube.com/watch?v=RkqQK7p5LhM",
            imageUrl = Banner.RESULTS,
            primaryButtonText = "Check Results",
            primaryButtonAction = "https://keralaresults.nic.in/"
        ),
        "edu_certificates" to MenuNode(
            name = "Certificates",
            iconUrl = GovIcons.CERTIFICATE,
            order = 4,
            parentId = "root_education",
            text = "Get duplicate / verified copies of SSLC book, Plus Two Mark " +
                    "List, Migration Certificate, Transfer Certificate, and Equivalency " +
                    "Certificate. Apply online via Pareeksha Bhavan or your university. " +
                    "Apostille and embassy attestation services for studying/working " +
                    "abroad are available through Norka Roots.",
            youtubeUrl = "https://www.youtube.com/watch?v=v9oXfX8hgQM",
            imageUrl = Banner.EDU_CERT,
            primaryButtonText = "Apply for Certificate",
            primaryButtonAction = "https://pareekshabhavan.kerala.gov.in/"
        ),
        "edu_schools" to MenuNode(
            name = "Schools",
            iconUrl = GovIcons.SCHOOL,
            order = 5,
            parentId = "root_education",
            text = "Find schools (Government, Aided, Unaided, CBSE, ICSE) near your " +
                    "location with details on infrastructure, faculty, results, and " +
                    "fees through Sametham/Samagra portal. View school code, contact " +
                    "details, head-master, and academic calendar. Useful when shifting " +
                    "schools or for TC requests.",
            youtubeUrl = "https://www.youtube.com/watch?v=9zJk6Qx0UVY",
            imageUrl = Banner.SCHOOL,
            primaryButtonText = "Find Schools",
            primaryButtonAction = "https://education.kerala.gov.in/"
        ),
    )


    private val kseb = mapOf(
        "kseb_bill" to MenuNode(
            name = "Bill Payment",
            iconUrl = GovIcons.BILL,
            order = 1,
            parentId = "root_kseb",
            text = "Pay your KSEB electricity bill online using consumer number. " +
                    "View current bill, previous bills, payment history, and download " +
                    "receipts. Multiple payment options: UPI, debit/credit card, net " +
                    "banking. Pay before due date to avoid disconnection and 1.25% " +
                    "monthly surcharge on overdue amounts.",
            youtubeUrl = "https://www.youtube.com/watch?v=Vq9Bq8Jx0fY",
            imageUrl = Banner.ELEC_BILL,
            primaryButtonText = "Pay Bill",
            primaryButtonAction = "https://wss.kseb.in/"
        ),
        "kseb_connection" to MenuNode(
            name = "New Connection",
            iconUrl = GovIcons.CONNECTION,
            order = 2,
            parentId = "root_kseb",
            text = "Apply for a new domestic, commercial, agricultural, or temporary " +
                    "electricity connection. Submit ownership/occupancy proof, ID proof, " +
                    "site sketch, and the wireman certificate. Standard release time is " +
                    "7-15 days for areas with existing infrastructure. Single-phase up " +
                    "to 5 kW; three-phase above that.",
            youtubeUrl = "https://www.youtube.com/watch?v=N3pLoG0fPeI",
            imageUrl = Banner.ELEC_CONN,
            primaryButtonText = "Apply for Connection",
            primaryButtonAction = "https://wss.kseb.in/selfservices/newConnQuickView"
        ),
        "kseb_complaint" to MenuNode(
            name = "Complaints",
            iconUrl = GovIcons.COMPLAINT,
            order = 3,
            parentId = "root_kseb",
            text = "Register complaints for power outages, voltage fluctuation, " +
                    "meter problems, billing errors, line damage, or pole/transformer " +
                    "issues. 24×7 KSEB Customer Care: 1912. Section officers must " +
                    "respond within stipulated time as per Kerala State Electricity " +
                    "Regulatory Commission's Standards of Performance.",
            youtubeUrl = "https://www.youtube.com/watch?v=fA0vF-xZqVY",
            imageUrl = Banner.HELPLINE,
            primaryButtonText = "Call 1912",
            primaryButtonAction = "tel:1912"
        ),
        "kseb_usage" to MenuNode(
            name = "Usage History",
            iconUrl = GovIcons.USAGE,
            order = 4,
            parentId = "root_kseb",
            text = "Track your monthly/bimonthly electricity usage in units (kWh) " +
                    "with graphical history of the past 12 months. Identify high-usage " +
                    "patterns, compare with neighbours' average, and get tips to save " +
                    "electricity. Useful for households on slab tariffs trying to stay " +
                    "below higher slabs (250/300/500 unit thresholds).",
            youtubeUrl = "https://www.youtube.com/watch?v=Wn-x4YKJpW0",
            imageUrl = Banner.USAGE,
            primaryButtonText = "View Usage",
            primaryButtonAction = "https://wss.kseb.in/"
        ),
        "kseb_status" to MenuNode(
            name = "Meter Status",
            iconUrl = GovIcons.METER,
            order = 5,
            parentId = "root_kseb",
            text = "Check meter reading status, last reading date, smart-meter live " +
                    "data (where installed), and request meter testing/replacement if " +
                    "the meter is faulty. KSEB is rolling out smart prepaid meters " +
                    "across the state under the RDSS scheme — these enable real-time " +
                    "usage tracking via the WSS app.",
            youtubeUrl = "https://www.youtube.com/watch?v=tQf3YwJ_3Vs",
            imageUrl = Banner.METER,
            primaryButtonText = "Check Meter",
            primaryButtonAction = "https://wss.kseb.in/"
        ),
    )


    private val water = mapOf(
        "water_bill" to MenuNode(
            name = "Water Bill",
            iconUrl = GovIcons.BILL,
            order = 1,
            parentId = "root_water",
            text = "Pay your Kerala Water Authority (KWA) bill online using consumer " +
                    "number. View current bill, past bills, and payment history. KWA " +
                    "issues bimonthly bills based on metered consumption. Pay before due " +
                    "date to avoid disconnection charges and reconnection fees.",
            youtubeUrl = "https://www.youtube.com/watch?v=lZ8Pk4N4mXk",
            imageUrl = Banner.WATER_BILL,
            primaryButtonText = "Pay Water Bill",
            primaryButtonAction = "https://www.kwa.kerala.gov.in/"
        ),
        "water_connection" to MenuNode(
            name = "New Connection",
            iconUrl = GovIcons.CONNECTION,
            order = 2,
            parentId = "root_water",
            text = "Apply for a new domestic, commercial, or industrial water " +
                    "connection from KWA. Submit ownership proof, ID proof, location " +
                    "sketch, and pay the deposit + connection fee. Time to release " +
                    "depends on availability of pipeline in the area — typically 15-45 " +
                    "days. Annual maintenance charge (AMC) applies thereafter.",
            youtubeUrl = "https://www.youtube.com/watch?v=oG3vY8X5b0c",
            imageUrl = Banner.WATER_CONN,
            primaryButtonText = "Apply for Connection",
            primaryButtonAction = "https://www.kwa.kerala.gov.in/"
        ),
        "water_complaint" to MenuNode(
            name = "Complaints",
            iconUrl = GovIcons.COMPLAINT,
            order = 3,
            parentId = "root_water",
            text = "Register complaints for no/low water supply, leakage, " +
                    "contamination, billing issues, or meter problems. 24×7 KWA toll-free: " +
                    "1916. Issues are routed to the section/sub-division office and " +
                    "should be resolved within 24-72 hours depending on the type. " +
                    "Track complaint status using the docket number.",
            youtubeUrl = "https://www.youtube.com/watch?v=p8r9z3vG5dA",
            imageUrl = Banner.HELPLINE,
            primaryButtonText = "Call 1916",
            primaryButtonAction = "tel:1916"
        ),
        "water_quality" to MenuNode(
            name = "Water Quality",
            iconUrl = GovIcons.QUALITY,
            order = 4,
            parentId = "root_water",
            text = "Request water-quality testing for hardness, TDS, pH, chlorine, " +
                    "iron, fluoride, and bacterial contamination at KWA Water Quality " +
                    "Surveillance Labs. Recommended after suspected contamination, well " +
                    "drilling, or before installing a domestic RO system. View latest " +
                    "quality reports for your distribution zone.",
            youtubeUrl = "https://www.youtube.com/watch?v=h7vEbL9aNrA",
            imageUrl = Banner.WATER_QLY,
            primaryButtonText = "Test Quality",
            primaryButtonAction = "https://www.kwa.kerala.gov.in/"
        ),
        "water_tanker" to MenuNode(
            name = "Tankers",
            iconUrl = GovIcons.TANKER,
            order = 5,
            parentId = "root_water",
            text = "Book emergency water tanker supply (4,000–12,000 litre tankers) " +
                    "during shortage, summer, or pipeline disruption. KWA tankers serve " +
                    "houses, apartments, marriage halls, and institutions. Booking is " +
                    "based on availability and you will be charged per tanker + " +
                    "transport. Higher demand in March-May; book early.",
            youtubeUrl = "https://www.youtube.com/watch?v=u9yxZL8jGyA",
            imageUrl = Banner.TANKER,
            primaryButtonText = "Book a Tanker",
            primaryButtonAction = "https://www.kwa.kerala.gov.in/"
        ),
    )


    private val localGov = mapOf(
        "lg_tax" to MenuNode(
            name = "Property Tax",
            iconUrl = GovIcons.TAX,
            order = 1,
            parentId = "root_localgov",
            text = "Pay annual building tax (property tax) to your local Panchayat / " +
                    "Municipality / Corporation through the Sanchaya portal. Tax is " +
                    "based on plinth area, zone, age, and usage (residential, " +
                    "commercial, mixed). Receipt is required for trade licence renewal " +
                    "and many other certificates. Discount is available for early " +
                    "payment in some LSGs.",
            youtubeUrl = "https://www.youtube.com/watch?v=TtGyD5G6P7c",
            imageUrl = Banner.PROP_TAX,
            primaryButtonText = "Pay on Sanchaya",
            primaryButtonAction = "https://tax.lsgkerala.gov.in/epayment/"
        ),
        "lg_birth" to MenuNode(
            name = "Birth / Death Certificate",
            iconUrl = GovIcons.BIRTH,
            order = 2,
            parentId = "root_localgov",
            text = "Apply for or download digitally-signed Birth and Death " +
                    "Certificates registered with your LSG. Certificates registered " +
                    "after 2010 are mostly available online for download. For older " +
                    "records, visit the LSG office. Required for school admission, " +
                    "passport, insurance claims, and inheritance.",
            youtubeUrl = "https://www.youtube.com/watch?v=2lVnYJv4mNk",
            imageUrl = Banner.BIRTH,
            primaryButtonText = "Get Certificate",
            primaryButtonAction = "https://cr.lsgkerala.gov.in/"
        ),
        "lg_license" to MenuNode(
            name = "Licenses",
            iconUrl = GovIcons.LICENSE,
            order = 3,
            parentId = "root_localgov",
            text = "Apply for or renew Trade Licence (D&O Licence), Building Permit, " +
                    "PG/Hostel Licence, Hotel & Restaurant Licence, and Lodging Licence " +
                    "from your LSG. All licences require annual renewal. Operating " +
                    "without a valid licence attracts penalty + closure notice from " +
                    "the Health Inspector.",
            youtubeUrl = "https://www.youtube.com/watch?v=6gC5wHVe9N0",
            imageUrl = Banner.LICENSES,
            primaryButtonText = "Apply for Licence",
            primaryButtonAction = "https://sanchaya.lsgkerala.gov.in/"
        ),
        "lg_welfare" to MenuNode(
            name = "Welfare Schemes",
            iconUrl = GovIcons.WELFARE,
            order = 4,
            parentId = "root_localgov",
            text = "Browse welfare schemes implemented by Panchayats / Municipalities " +
                    "/ Corporations: old-age pension, widow pension, disability " +
                    "pension, unmarried-women pension, agriculture pension, " +
                    "scholarships for poor students, housing under LIFE Mission, and " +
                    "many sectoral schemes funded by the Plan Fund.",
            youtubeUrl = "https://www.youtube.com/watch?v=zKv3cT4NL4M",
            imageUrl = Banner.WELFARE,
            primaryButtonText = "View Schemes",
            primaryButtonAction = "https://lsgkerala.gov.in/"
        ),
        "lg_grievance" to MenuNode(
            name = "Grievance",
            iconUrl = GovIcons.GRIEVANCE,
            order = 5,
            parentId = "root_localgov",
            text = "Register a grievance against your LSG for delay in service " +
                    "delivery, unsatisfactory work, sanitation issues, road repair, " +
                    "street lights, or stray-animal menace. Escalate to the Ombudsman " +
                    "for Local Self Government Institutions if not resolved at the " +
                    "LSG level. Track status with grievance number.",
            youtubeUrl = "https://www.youtube.com/watch?v=Bg4xJ8uF_p4",
            imageUrl = Banner.GRIEVANCE,
            primaryButtonText = "File Grievance",
            primaryButtonAction = "https://cmo.kerala.gov.in/"
        ),
    )


    private val agriculture = mapOf(
        "agri_subsidy" to MenuNode(
            name = "Subsidies",
            iconUrl = GovIcons.SUBSIDY,
            order = 1,
            parentId = "root_agriculture",
            text = "Apply for input subsidies on seeds, fertilisers, pesticides, " +
                    "farm machinery (tillers, sprayers, harvesters), drip/sprinkler " +
                    "irrigation, polyhouses, dairy units, and goat/poultry farming. " +
                    "Subsidies range 25%–90% based on category (general, SC/ST, women, " +
                    "tribal). Apply via Krishi Bhavan or AIMS portal.",
            youtubeUrl = "https://www.youtube.com/watch?v=jKp2J2v8eXU",
            imageUrl = Banner.SUBSIDY,
            primaryButtonText = "View Subsidies",
            primaryButtonAction = "https://www.keralaagriculture.gov.in/"
        ),
        "agri_crops" to MenuNode(
            name = "Crop Info",
            iconUrl = GovIcons.CROP,
            order = 2,
            parentId = "root_agriculture",
            text = "Get scientifically-approved cultivation details for paddy, " +
                    "coconut, rubber, banana, vegetables, pepper, cardamom, tea, " +
                    "and coffee. Information includes variety, season, soil, spacing, " +
                    "fertiliser dosage, pest management, and harvesting. Sourced from " +
                    "Kerala Agricultural University Package of Practices.",
            youtubeUrl = "https://www.youtube.com/watch?v=BvR2C8N4LtU",
            imageUrl = Banner.CROP,
            primaryButtonText = "Crop Guide",
            primaryButtonAction = "https://www.kau.in/"
        ),
        "agri_insurance" to MenuNode(
            name = "Crop Insurance",
            iconUrl = GovIcons.INSURANCE,
            order = 3,
            parentId = "root_agriculture",
            text = "Enrol in Pradhan Mantri Fasal Bima Yojana (PMFBY), Kerala " +
                    "State Crop Insurance Scheme, and Coconut Palm Insurance Scheme. " +
                    "Insure against natural calamities, pest attack, and yield loss. " +
                    "Premium is heavily subsidised (1.5%-2% for Rabi/Kharif). Claims " +
                    "are settled within 60 days of crop-cutting experiments.",
            youtubeUrl = "https://www.youtube.com/watch?v=8PqfU0vXk4E",
            imageUrl = Banner.CROP_INS,
            primaryButtonText = "Enrol PMFBY",
            primaryButtonAction = "https://pmfby.gov.in/"
        ),
        "agri_market" to MenuNode(
            name = "Market Prices",
            iconUrl = GovIcons.MARKET,
            order = 4,
            parentId = "root_agriculture",
            text = "Live mandi prices for paddy, vegetables, fruits, pepper, " +
                    "rubber, coconut, copra, banana, and other crops across markets " +
                    "in Kerala and neighbouring states. Sourced from Agmarknet and " +
                    "VFPCK. Compare prices to choose the best market for your produce. " +
                    "Daily updated MSP information for paddy is also shown.",
            youtubeUrl = "https://www.youtube.com/watch?v=v1eF8T2L4XU",
            imageUrl = Banner.MARKET,
            primaryButtonText = "Today's Prices",
            primaryButtonAction = "https://agmarknet.gov.in/"
        ),
        "agri_schemes" to MenuNode(
            name = "Schemes",
            iconUrl = GovIcons.SCHEME,
            order = 5,
            parentId = "root_agriculture",
            text = "Central and State schemes for farmers: PM-KISAN (₹6,000/year), " +
                    "Kisan Credit Card, KCC interest subvention, Soil Health Card, " +
                    "Karshaka Pension, Aikya Karshaka Vedi assistance, Subhiksha " +
                    "Keralam, and Vegetable & Fruit Promotion Council Kerala (VFPCK) " +
                    "support schemes.",
            youtubeUrl = "https://www.youtube.com/watch?v=lMq8rR4P5_Q",
            imageUrl = Banner.SCHEME,
            primaryButtonText = "Explore Schemes",
            primaryButtonAction = "https://pmkisan.gov.in/"
        ),
    )


    private val egov = mapOf(
        "egov_cert" to MenuNode(
            name = "Certificates",
            iconUrl = GovIcons.CERTIFICATE,
            order = 1,
            parentId = "root_egov",
            text = "One-stop access to digitally-signed certificates issued by Kerala " +
                    "Government departments — Income, Caste, Domicile, Possession, " +
                    "Birth, Death, Marriage, EWS, Disability, and Non-Creamy Layer. " +
                    "Apply once, track status, download as PDF on your phone. Verifiable " +
                    "via QR code, accepted everywhere as the original.",
            youtubeUrl = "https://www.youtube.com/watch?v=6FQjJ2RhdEM",
            imageUrl = Banner.EGOV_CERT,
            primaryButtonText = "Open eDistrict",
            primaryButtonAction = "https://edistrict.kerala.gov.in/"
        ),
        "egov_id" to MenuNode(
            name = "Digital ID",
            iconUrl = GovIcons.DIGITAL_ID,
            order = 2,
            parentId = "root_egov",
            text = "Manage your digital identities — Aadhaar (UIDAI), DigiLocker, " +
                    "PAN, ABHA Health ID, mParivahan DL/RC, Voter ID, and Ration Card. " +
                    "Add, update, link, and verify documents in one place. DigiLocker " +
                    "documents are legally equivalent to physical originals under the " +
                    "IT Act, 2000.",
            youtubeUrl = "https://www.youtube.com/watch?v=p8RkCgN9N7M",
            imageUrl = Banner.DIGI_ID,
            primaryButtonText = "Open DigiLocker",
            primaryButtonAction = "https://www.digilocker.gov.in/"
        ),
        "egov_services" to MenuNode(
            name = "Services",
            iconUrl = GovIcons.SERVICES,
            order = 3,
            parentId = "root_egov",
            text = "Access 900+ government services from over 30 departments via " +
                    "the unified Kerala e-Sevanam portal. Single sign-on lets you apply, " +
                    "pay, and track applications across departments without separate " +
                    "logins. Services include certificates, licences, payments, " +
                    "permits, and grievances.",
            youtubeUrl = "https://www.youtube.com/watch?v=qRLcA3p4N3Y",
            imageUrl = Banner.EGOV_SERV,
            primaryButtonText = "Open e-Sevanam",
            primaryButtonAction = "https://services.kerala.gov.in/"
        ),
        "egov_status" to MenuNode(
            name = "Application Status",
            iconUrl = GovIcons.STATUS,
            order = 4,
            parentId = "root_egov",
            text = "Track real-time status of all your government applications " +
                    "(certificates, licences, scholarships, ration card, pension, " +
                    "passport, etc.) using the application/reference number. Get " +
                    "SMS / email alerts on each stage update — submitted, verified, " +
                    "approved, dispatched. View officer comments if any.",
            youtubeUrl = "https://www.youtube.com/watch?v=h6KzJ7Y2bM4",
            imageUrl = Banner.APP_STATUS,
            primaryButtonText = "Track Status",
            primaryButtonAction = "https://edistrict.kerala.gov.in/"
        ),
        "egov_support" to MenuNode(
            name = "Support",
            iconUrl = GovIcons.SUPPORT,
            order = 5,
            parentId = "root_egov",
            text = "Get help with using government online services. Akshaya " +
                    "Citizen Service Centres provide assisted-service across Kerala " +
                    "for those who cannot apply themselves. State-level helpdesk: " +
                    "0471-2335523 (KSITM). Email: support.edistrict@kerala.gov.in. " +
                    "Working hours: 10 AM–5 PM on government working days.",
            youtubeUrl = "https://www.youtube.com/watch?v=R3pY4Q5L9_s",
            imageUrl = Banner.SUPPORT,
            primaryButtonText = "Find Akshaya Centre",
            primaryButtonAction = "https://www.akshaya.kerala.gov.in/"
        ),
    )


    private fun buildAllNodes(): Map<String, MenuNode> {

        val level1 = mapOf(
            "root_mvd" to MenuNode(
                name = "MVD",
                iconUrl = GovIcons.CAR,
                order = 1,
                subMenuIds = subIds(*mvd.keys.toTypedArray()),
                text = "Motor Vehicles Department of Kerala — driving licences, " +
                        "vehicle registration, road tax, fitness, permits, and traffic fines."
            ),
            "root_police" to MenuNode(
                name = "Police",
                iconUrl = GovIcons.POLICE,
                order = 2,
                subMenuIds = subIds(*police.keys.toTypedArray()),
                text = "Kerala Police — file FIRs, report cyber crime, missing persons, " +
                        "and access emergency helplines 24×7."
            ),
            "root_health" to MenuNode(
                name = "Health",
                iconUrl = GovIcons.HEALTH,
                order = 3,
                subMenuIds = subIds(*health.keys.toTypedArray()),
                text = "Department of Health & Family Welfare — find hospitals, book " +
                        "appointments, vaccination, ABHA, and 108 ambulance service."
            ),
            "root_revenue" to MenuNode(
                name = "Revenue",
                iconUrl = GovIcons.LAND,
                order = 4,
                subMenuIds = subIds(*revenue.keys.toTypedArray()),
                text = "Revenue Department — land records, mutation, certificates " +
                        "(income, caste, domicile), property tax, and survey maps."
            ),
            "root_education" to MenuNode(
                name = "Education",
                iconUrl = GovIcons.EDUCATION,
                order = 5,
                subMenuIds = subIds(*education.keys.toTypedArray()),
                text = "General Education & Higher Education — admissions, " +
                        "scholarships, exam results, certificates, and school search."
            ),
            "root_kseb" to MenuNode(
                name = "Electricity",
                iconUrl = GovIcons.ELECTRICITY,
                order = 6,
                subMenuIds = subIds(*kseb.keys.toTypedArray()),
                text = "Kerala State Electricity Board — pay bills, new connections, " +
                        "complaints, usage history, and meter status."
            ),
            "root_water" to MenuNode(
                name = "Water",
                iconUrl = GovIcons.WATER,
                order = 7,
                subMenuIds = subIds(*water.keys.toTypedArray()),
                text = "Kerala Water Authority — water bill payment, new connection, " +
                        "complaints, water-quality testing, and tanker bookings."
            ),
            "root_localgov" to MenuNode(
                name = "Local Govt",
                iconUrl = GovIcons.LOCAL,
                order = 8,
                subMenuIds = subIds(*localGov.keys.toTypedArray()),
                text = "Panchayats, Municipalities & Corporations — building tax, " +
                        "birth/death certificates, trade licences, welfare, grievances."
            ),
            "root_agriculture" to MenuNode(
                name = "Agriculture",
                iconUrl = GovIcons.FARM,
                order = 9,
                subMenuIds = subIds(*agriculture.keys.toTypedArray()),
                text = "Department of Agriculture — subsidies, crop guidance, " +
                        "insurance, mandi prices, and farmer welfare schemes."
            ),
            "root_egov" to MenuNode(
                name = "e-Gov Services",
                iconUrl = GovIcons.DIGITAL,
                order = 10,
                subMenuIds = subIds(*egov.keys.toTypedArray()),
                text = "Unified Kerala e-Sevanam, eDistrict and DigiLocker access " +
                        "to 900+ government services across 30+ departments."
            ),
        )

        return level1 +
                mvd +
                police +
                health +
                revenue +
                education +
                kseb +
                water +
                localGov +
                agriculture +
                egov
    }


    fun seed(
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {},
    ) {
        val all = buildAllNodes()

        ref.setValue(all)
            .addOnSuccessListener {
                Log.d("FirebaseSeeder", "✅ Seeded ${all.size} nodes successfully")
                onSuccess()
            }
            .addOnFailureListener {
                Log.e("FirebaseSeeder", "❌ Seed failed", it)
                onError(it)
            }
    }
}