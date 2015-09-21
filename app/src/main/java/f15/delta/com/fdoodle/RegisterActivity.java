package f15.delta.com.fdoodle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegisterActivity extends Activity {

    AutoCompleteTextView dropdown;
    TextView collegeButton;
    TextView degreeButton;
    TextView branchButton;
    TextView yearButton;
    Button signUp;
    String[] items;
    String[] degree;
    String[] branch;
    String[] year;
    String mail,username,password,repassword,fullname,college_not="",phone,college_string,branch_string
            ,degree_string,year_string;
    EditText mail_text,username_text,password_text,repassword_text,fullname_text,college_not_present;
    EditText phone_text;
    Switch ynswitch;
    String promo="No";
    String strName;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //dropdown = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/gnu.ttf");
        collegeButton = (TextView)findViewById(R.id.collegeButton);
        degreeButton =(TextView)findViewById(R.id.degreeButton);
        branchButton = (TextView)findViewById(R.id.branchButton);
        yearButton = (TextView)findViewById(R.id.yearButton);
        signUp = (Button)findViewById(R.id.signUp);
        mail_text = (EditText)findViewById(R.id.mail_text);
        username_text = (EditText)findViewById(R.id.username_text);
        password_text = (EditText)findViewById(R.id.password_text);
        repassword_text = (EditText)findViewById(R.id.repassword_text);
        fullname_text = (EditText)findViewById(R.id.fullname_text);
        college_not_present = (EditText)findViewById(R.id.college_not_present);
        phone_text = (EditText)findViewById(R.id.phone_text);
        username_text.setTypeface(typeface);
        mail_text.setTypeface(typeface);
        fullname_text.setTypeface(typeface);
        college_not_present.setTypeface(typeface);
        ynswitch = (Switch)findViewById(R.id.on_off_switch);
        ynswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)promo="Yes";
                else promo="No";
            }

        });
        degree = new String[]{"BTECH",
                "BArch",
                "MCA",
                "MBA",
                "MSc",
                "MS",
                "BSc",
                "BCA",
                "BA",
                "MA",
                "Diploma",
                "Others",
        };
        branch = new String[]{
                "CSE",
                "IT",
                "ECE",
                "EEE",
                "EIE",
                "ICE",
                "Mech",
                "Prod",
                "Auto",
                "Civil",
                "Arch",
                "Aero",
                "Bio",
                "Arts",
                "Science",
                "Others",
        };
        year = new String[]{
                "1st year",
                "2nd year",
                "3rd year",
                "4th year",
                "5th year",
                "Others'"

        };
        items = new String[]{
                "A.C. College of Engineering and Technology","A.M.S College of Engineering", "Aalim Muhammed Salegh College Of Engineering", "Aarupadai Veedu Institute of Technology", "Vinayaka Mission University", "ABES Engineering College , Ghaziabad", "ABV-IIITM, Gwalior", "ACCET Karaikudi", "Acharaya Nagarjuna University", "Achariya Bala Siksha Mandir",
                "Achariya College of Engineering and Technology",
                "Acropolois Technical Campus",
                "ACT Anna University",
                "Adhiyamaan College of Engineering, Hosur",
                "Adithya Institute of Technology",
                "Agni College Of Technology",
                "Alagappa Chettiar College of Engineering and Technology, Karaikudi",
                "Al-Ameen Engineering College",
                "Al-Ameer College of Engineering and IT",
                "Alpha College of Engineering",
                "Alpha College of Engineering and Technology",
                "Amara Institute of Engineering and Technology",
                "American College",
                "AMET University",
                "Amity Institute of Space Science and Technology",
                "Amrita Vishwa Vidyapeetham, Amritapuri",
                "Amrita Vishwa Vidyapeetham, Bengaluru",
                "Amrita Vishwa Vidyapeetham, Coimbatore",
                "Amrita Vishwa Vidyapeetham, Kochi",
                "Amrita Vishwa Vidyapeetham, Mysore",
                "AMS College of Engineering",
                "Anand Institute of Higher Technology",
                "Andhra University",
                "Angel College of Engineering and Technology",
                "Anil Neerukonda Institute Of Technology and Science",
                "Anjalai Ammal Mahalingam Engineering College",
                "Anna University - BIT Campus Tiruchirappalli",
                "Anna University School of Architecture and Planning",
                "Annamacharya Institute of Technology and Sciences, Tirupati",
                "Annamalai university",
                "Anuradha Engineering College Chikhali",
                "Apollo Priyadarshanam Institute of Technology",
                "Army Institute of Management and Technology",
                "Arunai Engineering College",
                "Audisankara College of Engineering and Technology(ASCET)",
                "Aurora's Scientific and Technological Institute",
                "AVN Institute of Engineering and Technology",
                "B.S.Abdur Rahman University",
                "Babasaheb Bhimrao Ambedkar University",
                "Badruka College of Commerce and Arts",
                "Banasthali University",
                "Bandung Institute of Technology",
                "Bangalore Institute of Management Studies",
                "Bangalore Institute of Technology",
                "Bangladesh University of Engineering and Technology",
                "Bannari Amman Institute of Technology",
                "Bapatla Engineering College",
                "Bapatla Women's Engineering College",
                "Barathiyar University",
                "Bengal Engineering and Science University, Shibpur",
                "Bharat Institute of Engineering and Technology",
                "Bharath Niketan Engineering College",
                "Bharath University",
                "Bharathidasan Institute of Management",
                "Bharathidasan University",
                "Bharathiyar College of Engineering and Technology",
                "Bhoj Reddy Engineering College for Women",
                "Bhopal School of Social Sciences",
                "Birla Institute of Technology, Mesra",
                "Bishop Heber College",
                "BITS Pilani Hyderabad Campus",
                "BITS Pilani K.K. Birla Goa Campus",
                "BMS Engineering College",
                "BMS Institute of Technology",
                "Brindavan Institute of Technology and Science",
                "BSA Crescent Engineering College",
                "C. Abdul Hakeem College of Engineering and Technology",
                "Canara Engineering College",
                "CARE School of Engineering",
                "Carnegie Mellon University",
                "Cauvery College for Women ",
                " CBM Arts and Science College",
                "Central Electro Chemical Research Institute",
                "Central University of Las Villas",
                "Chaitanya Engineering College",
                "Chaithanya Bharathi Institute of Technology(CBIT)",
                "Chalapathi Institute of Engineering and Technology",
                "Chandubhai S Patel Institute of Technology(CSPIT)",
                "Channabasaveshwara Institute of Technology",
                "Chendhuran College of Engineering and Technology",
                "Chennai Film School",
                "Chennai institute of technology",
                "Chennai Mathematical Institute",
                "Chettinad College of Engineering and Technology",
                "Christ the King Engineering College",
                "Christ University",
                "Christu Jyoti Institute of Technology & Science(CJITS)",
                "CMR Engineering College",
                "CMR Institute of Technology",
                "CMR Technical Campus",
                "Cochin University of Science and Technology",
                "Coimbatore Institute of Engineering and Technology (CIET)",
                "Coimbatore Institute of Technology",
                "College of Applied Science, Vadakkencherry",
                "College of Engineering and Management Punnapra, Alappuzh",
                "College of Engineering and Technology, Bhubaneswar",
                "College of Engineering Chengannur",
                "College of Engineering Thalassery",
                "College of Engineering Trivandrum, Kerala",
                "College of Engineering, Guindy",
                "College of Technology, Pantnagar",
                "Comenius University in Bratislava",
                "CSI College of Engineering",
                "CVR College of Engineering",
                "D.G Vaishnav college",
                "D.R.K. College of Engineering and Technology",
                "Delhi College Of Engineering",
                "Delhi University",
                "Dhanalakshmi College of Engineering",
                "Dhanalakshmi Srinivasan College of Engineering and Technology",
                "Dhirajlal Gandhi College Of Technology",
                "DKTE Society's Textile and Engineering Institute, Ichalkaranji",
                "DMI college of engineering",
                "DMS Pondicherry University",
                "Dr. Mahalingam College of Engineering and Technology",
                "Dr. N.G.P. Institute of Technology",
                "Dr. Nallini Institution of Engineering and Technology",
                "Dr. RMLS College, Muzaffarpur",
                "Dr. S.J.S Paul Memorial College of Engineering and Technology",
                "Dr.RR & SR Technical University",
                "Dronacharya College of Engineering",
                "DVR & DHS MIC College of Technology",
                "Easwari Engineering College",
                "EGS Pillay Engineering College",
                "Einstein College of Engineering",
                "ER & DCI Institute of Technology",
                "Er.Perumal Manimekalai College of Engineering",
                "Erode Builder Educational Trust's Group of Institutions",
                "Ethiraj college for Women",
                "FEAT Annamalai University",
                "Francis Xavier Engineering College",
                "G Pullaiah College of Engineering and Technology",
                "G. Narayanamma Institute of Technology and Science",
                "G.K.M College of Engineering and Technology",
                "G.Pulla Reddy Engineering College",
                "Gandhi Institute for Technogical Advancement",
                "Gandhigram Rural institute",
                "Gayatari Vidya Parishad College Of Engineering",
                "GITAM Institute of Management",
                "GITAM Institute of Science",
                "GITAM Institute of Technology, Hyderabad",
                "GITAM Institute of Technology, Vizag",
                "GITAM School of Technology, Bengaluru",
                "GKM College of Engineering and Technology",
                "Gnanamani College of Engineering",
                "Gokaraju Rangaraju Institute of Engineering and Technology",
                "Gossener College",
                "Government College of Engineering, Salem",
                "Government College of Engineering, Tirunelveli",
                "Government College of Technology, Coimbatore",
                "Government Engineering College, Thrissur",
                "Government Engineering College, Trivandrum",
                "Government Model Engineering College",
                "Great Lakes Institute of Management",
                "GRT Institute of Engineering and Technology",
                "Gudlavalleru Engineering College",
                "Gurgaon Institute of Technology",
                "Guru Ghasidas Vishwavidyalaya",
                "Guru Nanak College",
                "Gyan Ganga College of Technology",
                "Gyan Vihar School of Engineering and Technology Jaipur",
                "Haldia Institute of Technology",
                "Hindustan College of Engineering",
                "IET DAVV,INDORE",
                "Image College of Arts, Animation and Technology",
                "Imperial College London",
                "Indian Institute of Information Technology Design and Manufacture, Kancheepuram",
                "Indian Institute of Information Technology, Allahabad",
                "Indian Institute of Information Technology, Bhubaneswar",
                "Indian Institute of Information Technology, Design and Manufacturing (IIITDM)",
                "Indian Institute of Information Technology, Hyderabad",
                "Indian Institute of Management Bangalore",
                "Indian Institute of Management Calcutta",
                "Indian Institute of Management Trichy",
                "Indian Institute of Science Bangalore",
                "Indian Institute of Science Education and Research, Kolkata",
                "Indian Institute of Science Education and Research, Pune",
                "Indian Institute of Science Education and Research, Thiruvananthapuram",
                "Indian Institute of Space Science and Technology",
                "Indian Institute of Technology Bombay",
                "Indian Institute of Technology Delhi",
                "Indian Institute of Technology Guwahati",
                "Indian Institute of Technology Kanpur",
                "Indian Institute of Technology Madras",
                "Indian Institute of Technology Roorkee",
                "Indian Institute of Technology ROPAR",
                "Indian Institute of Technology, Jodhpur",
                "Indian Institute of Technology, Kharagpur",
                "Indian Railways Institute of Mechanical and Electrical Engineering",
                "Indian School of Mines, Dhanbad",
                "Indira Gandhi Institute of Technology",
                "Indra Ganesan College of Engineering",
                "Infant Jesus College of Engineering",
                "Info Institute of Engineering",
                "Institute of Chemical Technology",
                "Institute of Engineering and Management, Kolkata",
                "Institute of Engineering and Technology",
                "Institute Of Engineering and Technology,DAVV",
                "Institute of Hotel Management Catering Technology and Applied Nutrition, Mumbai",
                "Institute of Management, Nirma University",
                "Institute of Road and Transport Technology",
                "Institute of Science and Technology",
                "Institute of Technology and Management",
                "Institute of Technology and Sciences(ITS), Bhiwani",
                "International Institute for Population Sciences",
                "International Institute of Information Technology, Bangalore",
                "International Institute of Information Technology, Hyderabad",
                "J J College of Engineering and Technology",
                "J. B. Institute of Engineering and Technology",
                "Jadavpur University",
                "Jawahar Engineering College",
                "Jawaharlal Nehru Technological University",
                "Jay Shri Ram Group of Institution",
                "Jaya Engineering College",
                "Jayam College of Engineering and Technology",
                "Jayaram College of Engineering and Technology",
                "Jaypee Institute of Information Technology",
                "Jeppiaar Engineering College",
                "JNTUA College of Engineering Pulivendula",
                "Jodhpur Institute of Engineering & Technology",
                "Johns Hopkins University",
                "JSS Academy Of Technical Education",
                "Jyothi Engineering College",
                "K Ramakrishnan College of Technology",
                "K S Rangasamy College of Technology",
                "K.L.N College Of Engineering",
                "K.L.N College of Information Technology",
                "Kakatiya Institute of Technology and Science, Warangal",
                "Kakatiya University",
                "Kalaignar Karunanidhi Institute of Technology",
                "Kalasalingam Institute of Technology",
                "Kalasalingam University",
                "Kalinga Institute of Industrial Technology",
                "Kamaraj College of Engineering and Technology",
                "Kanchi Pallavan Engineering College",
                "Karpaga Vinayaga College of Engineering and Technology",
                "Karpagam College of Engineering",
                "Karpagam University",
                "Karunya University",
                "Kathir College of Engineering",
                "KCG College of Technology",
                "KGISL Institute Of Technology",
                "Kings College of Engineering",
                "KIT'S College of Engineering , Kolhapur",
                "KKR & KSR Institute of Technology and Sciences",
                "Knowledge Institute of Technology(KIOT)",
                "Kolkata Institute of Technology",
                "Koneru Lakshmaiah College of Engineering",
                "Kongu Engineering College",
                "Kongunadu College of Engineering and Technology",
                "KPR Institute of Engineering and Technology",
                "KS School of Engineering and Management",
                "Kumaraguru College of Technology, Coimbatore",
                "Kuppam engineering college",
                "L. J. Institute of Engineering and Technology",
                "Laki Reddy Bali Reddy College of Engineering",
                "Lakshmi Narain College of Technology",
                "Latha Mathavan Engineering College",
                "Lovely Professional University",
                "Loyola Institute of Business Administration",
                "Loyola-ICAM College of Engineering and Technology",
                "M. Kumarasamy College of Engineering",
                "M.A.M College of Engineering and Technology",
                "M.A.M School of Engineering",
                "M.A.M. School of Architecture",
                "M.A.R College of Engineering and Technology",
                "M.I.E.T  ENGINEERING COLLEGE",
                "M.O.P. Vaishnav College for Women",
                "M.S. Ramaiah Institute of Technology",
                "M.V.S.R Engineering College",
                "Maamallan Institute of Technology",
                "Madha Institute of Engineering and Technology",
                "Madras Christian College",
                "Madras Institute of Technology",
                "Madras University",
                "Madurai Institute Of Engineering and Technology",
                "Magna College of Engineering",
                "Maharaja Agrasen Institute of Technology",
                "Maharaja Prithvi Engineering College",
                "Maharaja Sayajirao University of Baroda",
                "Maharshi Arvind Institute of Science and Management",
                "Mahatma Gandhi Institute of Technology",
                "Mahendra Engineering College",
                "Mahendra Institute of Technology",
                "Mailam Engineering College",
                "Malaviya National Institute of Technology, Jaipur",
                "Malla Reddy Engineering College",
                "Management Development Institute",
                "Manakula Vinayagar Institute of Technology",
                "Manav Rachna International University",
                "Mangalam College of Engineering",
                "Manipal Institute of Technology",
                "Mannar Thirumalai Naicker College",
                "Mar Athanasius College of Engineering",
                "Mar Baselios College of Engineering and Technology",
                "Maturi Venkata Subba Rao Engineering College",
                "Maulana Azad National Institute of Technology, Bhopal",
                "Medi-Caps Institute of Technology and Management",
                "Meenakshi College Of Engineering",
                "Meenakshi Sundararajan Engineering College",
                "Mepco Schlenk Engineering College",
                "MES College of Engineering",
                "MIT Campus, Anna University",
                "MNIT Jaipur",
                "MNM Jain Engineering College",
                "Model Engineering College",
                "Modern Education Society's College of Engineering",
                "Mohamed Sathak Engineering College",
                "Mohandas College of Engineering and Technology",
                "Moogambigai College of Engineering",
                "Motilal Nehru National Institute of Technology, Allahabad",
                "Muffakham Jah College of Engineering and Technology",
                "Mumbai University",
                "Muzaffarpur Institute of Technology",
                "MVGR College of Engineering",
                "MVJ College of Engineering",
                "Nalanda Degree College",
                "Nandha Engineering College",
                "Narasu's Sarathy Institute of Technology",
                "National Engineering college",
                "National Institute of Engineering",
                "National Institute Of Science And Technology",
                "National Institute of Technology, Agartala",
                "National Institute of Technology, Arunachal Pradesh",
                "National Institute of Technology, Calicut",
                "National Institute of Technology, Delhi",
                "National Institute of Technology, Durgapur",
                "National Institute of Technology, Goa",
                "National Institute of Technology, Hamirpur",
                "National Institute of Technology, Jalandhar",
                "National Institute of Technology, Jamshedpur",
                "National Institute of Technology, Karnataka, Surathkal",
                "National Institute of Technology, Tiruchirapalli",
                "National Institute of Technology, Kurukshetra",
                "National Institute of Technology, Manipur",
                "National Institute of Technology, Mizoram",
                "National Institute of Technology, Nagaland",
                "National Institute of Technology, Patna",
                "National Institute of Technology, Puducherry",
                "National Institute of Technology, Raipur",
                "National Institute of Technology, Rourkela",
                "National Institute of Technology, Sikkim",
                "National Institute of Technology, Silchar",
                "National Institute of Technology, Srinagar",
                "National Institute of Technology, Uttarakhand",
                "National Institute of Technology, Warangal",
                "National Institute of Technology,Meghalaya",
                "National Research University of Information Technologies,Mechanics and Optics (NRU ITMO)",
                "National University of Singapore (NUS)",
                "Nehru Institute of Engineering and Technology",
                "Netaji Subhas Institute of Technology (NSIT)",
                "Nirma University of Science & Technology",
                "Nitte Mahalinga Adyanthaya Memorial Institute of Technology (NMAMIT)",
                "Northern India Engineering College",
                "NPR College of Engineering & Technology (NPRCET)",
                "NRI INSTITUE OF TECHNOLOGY (NRIIT)",
                "Odaiyappa College of Engineering and Technology (OCET)",
                "Oriental Institute of Science and Technology (OIST)",
                "Oxford College of Engineering and Technology",
                "P. R. Engineering College (PREC)",
                "P.B. College Of Engineering (PBCE)",
                "P.M.R Engineering College",
                "P.S.G COLLEGE OF TECHNOLOGY ",
                "P.S.R.ENGINEERING COLLEGE",
                "PA College of Engineering and Technology",
                "Paavai College of Engineering",
                "Padmasri Dr. B.V Raju Institute of Technology (BVRIT)",
                "Panimalar Engineering College",
                "Pannaikadu Veerammal Paramasivam College of Engineering and Technology (PVP college of Engineering & Technology)",
                "Parisutham Institute of Technology and Science(PITS)",
                "Park College of Engineering and Technology (PCET)",
                "Pathfinder Engineering College",
                "PBR Visvodaya Institute of Technology and Science(PBR VITS)",
                "People's Education Society Institute of Technology,South Campus (PESIT)",
                "periyar maniammai university (PMU)",
                "PERUNTHALAIVAR KAMARAJAR INSTITUTE OF ENGINEERING AND TECHNOLOGY (PKIET)",
                "PET Engineering College",
                "Podhigai College of Engineering & Technology",
                "Pondicherry University",
                "Ponnaiyah Ramajayam College of Engineering & Technology (PRCET)",
                "Prasad v. Potluri Siddhartha Institute of Technology (PVP Siddhartha Institute of Technology)",
                "PRATHYUSHA INSTITUTE OF TECHNOLOGY AND MANAGEMENT",
                "Prince Shri Venkateshwara Padmavathy Engineering College",
                "PRIST University",
                "PSG College of Arts and Science",
                "PSG POLYTECHNIC COLLEGE",
                "PSNA College of Engineering and Technology (PSNA CET)",
                "Pune Institute of Computer Technology (PICT)",
                "Pune Vidhyarthi Grihas College of Engineering and Technology (PVGCOET)",
                "R V R & J C College of Engineering (Rayapati Venkata Rangarao & Jagarlamudi Chandramouli)",
                "R. N. Modi Engineering College (RMEC)",
                "R.M.K Engineering College",
                "R.M.K. College of Engineering & Technology(RMKCET)",
                "Raja College of Engineering and Technology (RajaCET)",
                "Rajagiri College Of Social Sciences",
                "Rajagiri School of Engineering and Technology",
                "Rajalakshmi Engineering College",
                "Rajalakshmi Institute of Technology",
                "Rajiv Gandhi College of Engineering and Technology (RGCET)",
                "Rama Nagappa Shetty(RNS) Institute of Technology and Management Studies ",
                "Ramakrishna Mission Vivekananda College",
                "Ranganathan Engineering College",
                "Ranipettai Engineering College",
                "Rashtreeya Vidyalaya College of Engineering (RV College of Engineering)",
                "RMD Engineering College",
                "Roever College of Engineering & Technology (Roever Tech)",
                "Royal College of Engineering & Technology (RCET)",
                "RRASE COLLEGE OF ENGINEERING",
                "RSR Engineering College",
                "Rungta College of Engineering & Technology (RCET)",
                "RVS College of Engineering and Technology",
                "S.A. Engineering College",
                "Saarland University",
                "SACS MAVMM Engineering College",
                "Sanjay Ghodawat Group of Institute",
                "Sant Longowal Institute of Engineering and Technology (SLIET)",
                "Saranathan College of Engineering",
                "Sardar Raja College of Engineering(SRCE)",
                "Sardar Vallabhbhai National Institute of Technology (SVNIT)",
                "Sasi Institute of Technology & Engineering",
                "SASTRA University(Shanmugha Arts, Science, Technology & Research Academy)",
                "Sasurie College of Engineering",
                "Sathyabama University/Sathyabama Institute of Science and Technology",
                "Saveetha Engineering College",
                "Saveetha University",
                "SBM College of Engineering and Technology (SBMCET)",
                "School of Computer Science & Information Technology. Devi Ahilya Vishwavidyalaya",
                "School of Engineering,Cochin University of Science and Technology (CUSAT)",
                "Seethalakshmi Ramaswami college",
                "Selvam College of Technology",
                "Sengunthar Engineering College, Tiruchengode, Namakkal",
                "Sengunthar Engineering College, Erode",
                "Seth Jai Parkash Mukand Lal Institute of Engineering and Technology",
                "Sethu Institute Of Technology",
                "shanmuganathan engineering college",
                "Shivani College of Engineering & Technology(SCET)",
                "Shree Motilal Kanhaiyalal Fomra Institute of Technology",
                "Shreenivasa Engineering College(SEC)",
                "Shri Angalamman College of Engineering and Technology (SACET)",
                "Shri Ramswaroop Memorial College of Engineering and Management (SRMCEM)",
                "SHRI SAPTHAGIRI INSTITUTE OF TECHNOLOGY (SSIT)",
                "Sikkim Manipal University SMU",
                "SKP Engineering College(SKPEC)",
                "SKP Institute of Technology SKPIT",
                "SKR Engineering College SKREC",
                "SMK Fomra Institute of Technology",
                "SNS COLLEGE OF TECHNOLOGY",
                "SONA COLLEGE OF TECHNOLOGY.",
                "SREE BUDDHA COLLEGE OF ENGINEERING",
                "Sree Chitra Thirunal College of Engineering SCTCE",
                "SREE SAKTHI ENGINEERING COLLEGE",
                "Sree Sastha Institute of Engineering and Technology (SSIET) ",
                "Sree sowdambika college of engineering",
                "Sree Vidyanikethan Engineering College",
                "Sreenidhi Institute of Science and Technology",
                "Sreyas Institute of Engineering & Technology",
                "Sri Bhagawan Mahaveer Jain College",
                "Sri Chaitanya Degree College",
                "Sri Chandrasekarendra Saraswathi Viswa Maha Vidyalaya (SCSVMV)",
                "Sri Eshwar College of Engineering",
                "Sri Ganesh College Of Engineering and Technology ( SGCET )",
                "SRI JAYACHAMARAJENDRA COLLEGE OF ENGINEERING (SJCE)",
                "Sri Krishna College of Engineering & Technology (SKCET)",
                "Sri Manakula Vinayagar Engineering College",
                "Sri Muthukumaran Institute Of Technology SMIT",
                "Sri Nandhanam College of Engineering & Technology",
                "Sri Padmavati Mahila Visvavidyalayam (SPMVV)",
                "Sri Ram Engineering College",
                "Sri Ramakrishna Engineering College",
                "Sri Sai Ram Engineering college",
                "Sri Shakthi Institute of Engineering and Technology",
                "Sri Sivasubramaniya Nadar College (SSN) of Engineering",
                "Sri Venkateswara College of Engineering (SVCE)",
                "Sri Venkateswara University",
                "Sridevi Womens Engineering College",
                "Srinivasa Institute of Engineering and Technology",
                "Srinivasan Engineering College",
                "Sriram Engineering College",
                "SRM University",
                "SSR Engineering College",
                "St. Berchmans(SB) College",
                "St. Joseph's College of Engineering and Technology",
                "St.Joseph's College, Trichy ",
                " St. Joseph's Institute of Technology",
                "St. Mary's Engineering College",
                "St. Peter's College of Engineering and Technology",
                "St. Xavier's College",
                "Stanford University",
                "Star Lion College of Engineering and Technology",
                "Sudharsan Engineering College",
                "Supreme Knowledge Foundation Group of Institution",
                "SV National Institute of Technology, Surat",
                "SVU College of Engineering, Sri Venkateswara University",
                "Swarnandhra College of Engineering and Technology",
                "Symbiosis Institute of International Business",
                "T J Institute of Technology",
                "T.K.M College of Engineering",
                "Tagore Engineering College",
                "Tamilnadu Engineering College",
                "Tbilisi State University",
                "Techno India College of Technology",
                "Tejaa Shakthi Institute of Technology for Women",
                "Texas A&M University, Qatar",
                "Thadomal Shahani Engineering College",
                "Thangal Kunju Musaliar College of Engineering, Kollam",
                "Thanthai Periyar Government College Of Technology",
                "Thapar University",
                "The University of Chicago",
                "The University of Texas at Dallas",
                "Thiyagarajar College of Engineering",
                "Thiyagarajar School of Management",
                "Toc H Institute of Science And Technology",
                "Trichy Engineering College",
                "Triumphant Institute of Management Education",
                "TRP Engineering College",
                "Ultra College of Engineering and Technology for Women",
                "United Institute of Technology",
                "University at Buffalo",
                "University College of Engineering Panruti",
                "University College of Engineering Tindivanam",
                "University College of Engineering Villupuram",
                "University College of Engineering, Pattukkottai",
                "University Institute of Engineering and Technology, Panjab University",
                "University of California, Santa Barbara",
                "University of Cape Town",
                "University of Chicago",
                "University of Houston",
                "University of Kalyani",
                "University of Pittsburgh",
                "University of Pune",
                "University of Rajasthan",
                "Unnamalai Institute of Technology",
                "Urumu Dhanalakshmi College",
                "Usha Rama College of Engineering and Technology",
                "V.R. Siddhartha Engineering College",
                "V.S.B Engineering college, Karur",
                "Valliammai Engineering College",
                "Vandayar Engineering College",
                "Vardhaman College of Engineering",
                "Varuvan Vadivelan Institute of Technology",
                "Vasireddy Venktadri Institute of Technology",
                "Veerammal Engineering College",
                "Veermata Jijabai Technological Institute",
                "Vel Tech High Tech, Dr.Rangarajan Dr.Sakunthala Engineering College",
                "Velagapudi Ramakrishna Siddhartha Engineering College",
                "Velalar College of Engineering and Technology",
                "Vellamal College of Engineering and Technology",
                "Vellamal Engineering College",
                "Vellamal Institute of Technology",
                "Vels University",
                "Veltech University",
                "Vidhya Jyothi Institute of Technology",
                "Vidyaa Vikas College of Engineering and Technology",
                "Vignan's Lara Institute of Technology and Sciences",
                "Vinayaka Mission's Kirupananda Variyar Engineering College",
                "Visvesvarya National Institute of Technology, Nagpur",
                "VIT University, Chennai",
                "VIT University, Vellore",
                "VITS College of Engineering",
                "Vivekananda Institute of Technology",
                "Vivekanandha College of Engineering for Women",
                "VKS College of Engineering and Technology",
                "VRS college of Engineering and Technology",
                "VSB Enginnering College",
                "Vyas Institutes of Higher Education, Jodhpur",
                "Xavier Labour Relations Institute",
                "Zagreb Faculty of Electrical Engineering and Computing",
                "Zhejiang University",
                "Others"

        };
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, degree);
        //dropdown.setThreshold(1);
        //dropdown.setAdapter(adapter);
    }
    public  void callAlert(String[] array,String desc,final int t){
        // strName = "";

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                RegisterActivity.this);
        builderSingle.setTitle(desc);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                RegisterActivity.this,
                android.R.layout.select_dialog_singlechoice);
        for(int i=0;i<array.length;i++)
            arrayAdapter.add(array[i]);

        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        strName = arrayAdapter.getItem(which);
                        switch(t){
                            case 1: college_string=strName;
                                collegeButton.setText(strName);
                                break;
                            case 2:   degree_string=strName;
                                degreeButton.setText(strName);
                                break;
                            case 3:  branch_string=strName;
                                branchButton.setText(strName);
                                break;
                            case 4:  year_string=strName;
                                yearButton.setText(strName);
                                break;
                        }
                        System.out.println(t);


                    }
                });
        builderSingle.show();
        // return strName;
    }
    public void callList(View V){
        callAlert(items, "Select your college:", 1);
        if(college_string!=null) {



            if (college_string.length() != 0)
                collegeButton.setError(null);
            if (college_string.length() <= 7) college_not_present.setError("Enter College");
        }
    }
    public void callDegree(View view){
        callAlert(degree, "Select Degree",2);
        degree_string=strName;
        if(degree_string.length()!=0)
            degreeButton.setError(null);
    }
    public void callBranch(View view){
        callAlert(branch, "Select your branch",3);
        branch_string=strName;
        if(branch_string.length()!=0)
            branchButton.setError(null);
    }
    public void callYear(View view){
        callAlert(year,"Select your year",4);
        year_string=strName;
        if(year_string.length()!=0)
            yearButton.setError(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }
    public void callSignUp(View view){

        mail = mail_text.getText().toString();
        username = username_text.getText().toString();
        password = password_text.getText().toString();
        repassword = repassword_text.getText().toString();
        fullname = fullname_text.getText().toString();
        college_not = college_not_present.getText().toString();
        phone = phone_text.getText().toString();
        if((college_not.length()<=4)||(college_not==null))college_not=" ";

        if(mail.length()==0)mail_text.setError("Invalid email");
        else if(!isValidEmail(mail))mail_text.setError("Invalid email");
        else if(username.length()==0)username_text.setError("Invalid username");
        else if(password.length()==0)password_text.setError("Invalid password");
        else if(repassword.length()==0)repassword_text.setError("Retype password");
        else if(!password.equals(repassword))repassword_text.setError("Password not match");
        else if(fullname.length()==0)fullname_text.setError("Invalid fullname");
        else if((college_string.length()==0)&&(college_not.length()==0))collegeButton.setError("Choose college");
        else if((college_string.equals("Others"))&&(college_not.length()==0)){
            collegeButton.setError(null);
            college_not_present.setError("Enter college");
        }
        else if(degree_string.length()==0)degreeButton.setError("Choose Degree");
        else if(branch_string.length()==0)branchButton.setError("Choose Branch");
        else if(year_string.length()==0)yearButton.setError("Choose year");
        else if(phone.length()!=10)phone_text.setError("Invalid number");
        else {

            callSendRequest();
            signUp.setEnabled(false);
        }





    }
    public void callSendRequest(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
        StringRequest postRequest = new StringRequest(Request.Method.POST, Utilities.url_reg,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject;
                        String message;
                        int reg_auth=3;

                        try {
                            jsonObject = new JSONObject(response);
                            reg_auth = jsonObject.getInt("status");
                            message = jsonObject.getString("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            message = "error";
                        }
                        switch (reg_auth){
                            case 3: Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                                signUp.setEnabled(true);
                                break;
                            case 2: Toast.makeText(RegisterActivity.this,message+"Please activate your account from mail",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                        }

                        pDialog.dismiss();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(RegisterActivity.this,"Please check your internet",Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                        signUp.setEnabled(true);
                    }

                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("user_name", username);
                params.put("user_fullname", fullname);
                params.put("user_email", mail);
                params.put("user_pass",password);
                params.put("user_repass",repassword);
                params.put("user_college",college_string);
                params.put("user_othercollege","");
                params.put("user_degree",degree_string);
                params.put("user_branch",branch_string);
                params.put("user_year",year_string);
                params.put("user_phone",phone);
                params.put("user_promotion",promo);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);


    }
    private static Boolean isValidEmail(String email)
    {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
