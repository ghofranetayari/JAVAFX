/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ghofr
 */




import Utils.MyConnection;
import entities.Commentaire;
import entities.EVENNEMENT;
import entities.Tronslator;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.ComboBox;
import services.CommentaireCrud;


/**
 * FXML Controller class
 *
 * @author saif chaalene
 */
public class DetailWindowController implements Initializable {

    Connection cnx2;

    public DetailWindowController() {
        
        cnx2 = MyConnection.getInstance().getCnx();

    }
    
    @FXML
    private ComboBox<String> affCCC;

      @FXML
    private Label labeltraduction;
    @FXML
    private Label titreC;
    @FXML
    private Label typeG;
    @FXML
    private Label guideD;
    @FXML
    private Label decriptionE;
    @FXML
    private ImageView imageD;
    @FXML
    private TableView<Commentaire> tableviewC;
    @FXML
    private TableColumn<Commentaire, Integer> tvC;
    @FXML
    private TableColumn<Commentaire, String> tvCC;
    @FXML
    private TextField IDC;
    @FXML
    private TextArea contenuC;
    @FXML
    private Button ajouterC;
    @FXML
    private Button modifierC;
    @FXML
    private Button suprimerC;
    @FXML
    private Button translateC;

    
    
    
    
    //   private ResourceBundle bundle;
    /**
     * Initializes the controller class.
     */
    private int IdE;

    public void setSharedVariable(int IdE) {
        this.IdE = IdE;
         System.out.println(IdE);
    }

    private List<EVENNEMENT> evennements = new ArrayList<>();

    public List<EVENNEMENT> getData2() {

        List<EVENNEMENT> evennements = new ArrayList<>();
        EVENNEMENT evennement;
System.out.println("hne ykoli aba " + IdE);
        // System.out.println(IdE);
        try {

            String requete = "SELECT * FROM evenement WHERE  IdEvennement = ? ";
            PreparedStatement st = cnx2.prepareStatement(requete);

            st.setInt(1, IdE);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                EVENNEMENT evennemen = new EVENNEMENT();
                evennemen.setDescription(rs.getNString(3));
                evennemen.setGuide_id(rs.getInt(7));
                evennemen.setTitre(rs.getString(2));
                evennemen.setPrix(rs.getInt(5));
                evennemen.setImage(rs.getString(9));
                evennemen.setVille(rs.getString(8));
                evennemen.setDuree(rs.getString(10));
                evennemen.setIdEvenement(rs.getInt(1));
                evennements.add(evennemen);

                System.out.println(evennements);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return evennements;
    }

    
    private List<EVENNEMENT> evennement = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        String [] iteams={"traduire en francais","traduire en arabe","traduire en German"}  ;
    affCCC.getItems().addAll(iteams);
        // TODO

        evennement.addAll(getData2());

if (evennement.isEmpty()) {
    System.out.println("The 'evennement' list is empty.");
} else {
    System.out.println(evennement.get(0).getTitre());

 titreC.setText(evennement.get(0).getTitre()); 
 
 guideD.setText(Integer.toString(evennement.get(0).getGuide_id()));
 Image myimage = new Image(getClass().getResourceAsStream(evennement.get(0).getImage()));
       imageD.setImage(myimage);
 decriptionE.setText(evennement.get(0).getDescription());
 
 
 
}

      
    }

    @FXML
    void ajouterCC(ActionEvent event) {
        
        String input ="kes,2 girls 1 cup,2g1c,4r5e,5h1t,5hit,a_s_s,a55,acrotomophilia,alabama hot pocket,alaskan pipeline,anal,anilingus,anus,apeshit,ar5e,arrse,arse,arsehole,ass,ass-fucker,ass-hat,ass-jabber,ass-pirate,assbag,assbandit,assbanger,assbite,assclown,asscock,asscracker,asses,assface,assfuck,assfucker,assfukka,assgoblin,asshat,asshead,asshole,assholes,asshopper,assjacker,asslick,asslicker,assmonkey,assmunch,assmuncher,assnigger,asspirate,assshit,assshole,asssucker,asswad,asswhole,asswipe,auto erotic,autoerotic,axwound,b!tch,b00bs,b17ch,b1tch,babeland,baby batter,baby juice,ball gag,ball gravy,ball kicking,ball licking,ball sack,ball sucking,ballbag,balls,ballsack,bampot,bangbros,bareback,barely legal,barenaked,bastard,bastardo,bastinado,bbw,bdsm,beaner,beaners,beastial,beastiality,beaver cleaver,beaver lips,bellend,bestial,bestiality,bi+ch,biatch,big black,big breasts,big knockers,big tits,bimbos,birdlock,bitch,bitchass,bitcher,bitchers,bitches,bitchin,bitching,bitchtits,bitchy,black cock,blonde action,blonde on blonde action,bloody,blow job,blow your load,blowjob,blowjobs,blue waffle,blumpkin,boiolas,bollock,bollocks,bollok,bollox,bondage,boner,boob,boobs,booobs,boooobs,booooobs,booooooobs,booty call,breasts,breeder,brotherfucker,brown showers,brunette action,buceta,bugger,bukkake,bulldyke,bullet vibe,bullshit,bum,bumblefuck,bung hole,bunghole,bunny fucker,busty,butt,butt plug,butt-pirate,buttcheeks,buttfucka,buttfucker,butthole,buttmuch,buttplug,c0ck,c0cksucker,camel toe,camgirl,camslut,camwhore,carpet muncher,carpetmuncher,cawk,chesticle,chinc,chink,choad,chocolate rosebuds,chode,cipa,circlejerk,cl1t,cleveland steamer,clit,clitface,clitfuck,clitoris,clits,clover clamps,clusterfuck,cnut,cock,cock-sucker,cockass,cockbite,cockburger,cockeye,cockface,cockfucker,cockhead,cockjockey,cockknoker,cocklump,cockmaster,cockmongler,cockmongruel,cockmonkey,cockmunch,cockmuncher,cocknose,cocknugget,cocks,cockshit,cocksmith,cocksmoke,cocksmoker,cocksniffer,cocksuck,cocksucked,cocksucker,cocksucking,cocksucks,cocksuka,cocksukka,cockwaffle,cok,cokmuncher,coksucka,coochie,coochy,coon,coons,cooter,coprolagnia,coprophilia,cornhole,cox,cracker,crap,creampie,crotte,cum,cumbubble,cumdumpster,cumguzzler,cumjockey,cummer,cumming,cums,cumshot,cumslut,cumtart,cunilingus,cunillingus,cunnie,cunnilingus,cunt,cuntass,cuntface,cunthole,cuntlick,cuntlicker,cuntlicking,cuntrag,cunts,cuntslut,cyalis,cyberfuc,cyberfuck,cyberfucked,cyberfucker,cyberfuckers,cyberfucking,d1ck,dago,damn,darkie,date rape,daterape,deep throat,deepthroat,deggo,dendrophilia,dick,dick-sneeze,dickbag,dickbeaters,dickface,dickfuck,dickfucker,dickhead,dickhole,dickjuice,dickmilk ,dickmonger,dicks,dickslap,dicksucker,dicksucking,dicktickler,dickwad,dickweasel,dickweed,dickwod,dike,dildo,dildos,dingleberries,dingleberry,dink,dinks,dipshit,dirsa,dirty pillows,dirty sanchez,dlck,dog style,dog-fucker,doggie style,doggiestyle,doggin,dogging,doggy style,doggystyle,dolcett,domination,dominatrix,dommes,donkey punch,donkeyribber,doochbag,dookie,doosh,double dong,double penetration,doublelift,douche,douche-fag,douchebag,douchewaffle,dp action,dry hump,duche,dumass,dumb ass,dumbass,dumbcunt,dumbfuck,dumbshit,dumshit,dvda,dyke,eat my ass,ecchi,ejaculate,ejaculated,ejaculates,ejaculating,ejaculatings,ejaculation,ejakulate,erotic,erotism,escort,eunuch,f u c k,f u c k e r,f_u_c_k,f4nny,fag,fagbag,fagfucker,fagging,faggit,faggitt,faggot,faggotcock,faggs,fagot,fagots,fags,fagtard,fanny,fannyflaps,fannyfucker,fanyy,fatass,fcuk,fcuker,fcuking,fecal,feck,fecker,felch,felching,fellate,fellatio,feltch,female squirting,femdom,figging,fingerbang,fingerfuck,fingerfucked,fingerfucker,fingerfuckers,fingerfucking,fingerfucks,fingering,fistfuck,fistfucked,fistfucker,fistfuckers,fistfucking,fistfuckings,fistfucks,fisting,flamer,flange,foah,fook,fooker,foot fetish,footjob,frotting,fuck,fuck buttons,fuck off,fucka,fuckass,fuckbag,fuckboy,fuckbrain,fuckbutt,fuckbutter,fucked,fucker,fuckers,fuckersucker,fuckface,fuckhead,fuckheads,fuckhole,fuckin,fucking,fuckings,fuckingshitmotherfucker,fuckme,fucknut,fucknutt,fuckoff,fucks,fuckstick,fucktard,fucktards,fucktart,fucktwat,fuckup,fuckwad,fuckwhit,fuckwit,fuckwitt,fudge packer,fudgepacker,fuk,fuker,fukker,fukkin,fuks,fukwhit,fukwit,futanari,fux,fux0r,g-spot,gang bang,gangbang,gangbanged,gangbangs,gay,gay sex,gayass,gaybob,gaydo,gayfuck,gayfuckist,gaylord,gaysex,gaytard,gaywad,genitals,giant cock,girl on,girl on top,girls gone wild,goatcx,goatse,god damn,god-dam,god-damned,goddamn,goddamned,goddamnit,gokkun,golden shower,goo girl,gooch,goodpoop,gook,goregasm,gringo,grope,group sex,guido,guro,hand job,handjob,hard core,hard on,hardcore,hardcoresex,heeb,hell,hentai,heshe,ho,hoar,hoare,hoe,hoer,homo,homodumbshit,homoerotic,honkey,hooker,hore,horniest,horny,hot carl,hot chick,hotsex,how to kill,how to murder,huge fat,humping,incest,intercourse,jack Off,jack-off,jackass,jackoff,jaggi,jagoff,jail bait,jailbait,jap,jelly donut,jerk off,jerk-off,jerkass,jigaboo,jiggaboo,jiggerboo,jism,jiz,jizm,jizz,juggs,jungle bunny,junglebunny,kawk,kike,kinbaku,kinkster,kinky,knob,knobbing,knobead,knobed,knobend,knobhead,knobjocky,knobjokey,kock,kondum,kondums,kooch,kootch,kraut,kum,kummer,kumming,kums,kunilingus,kunja,kunt,kyke,l3i+ch,l3itch,labia,lameass,lardass,leather restraint,leather straight jacket,lemon party,lesbian,lesbo,lezzie,lmfao,lolita,lovemaking,lust,lusting,m0f0,m0fo,m45terbate,ma5terb8,ma5terbate,make me come,male squirting,masochist,master-bate,masterb8,masterbat,masterbat3,masterbate,masterbation,masterbations,masturbate,mcfagget,menage a trois,mick,milf,minge,missionary position,mo-fo,mof0,mofo,mothafuck,mothafucka,mothafuckas,mothafuckaz,mothafucked,mothafucker,mothafuckers,mothafuckin,mothafucking,mothafuckings,mothafucks,mother fucker,motherfuck,motherfucked,motherfucker,motherfuckers,motherfuckin,motherfucking,motherfuckings,motherfuckka,motherfucks,mound of venus,mr hands,muff,muff diver,muffdiver,muffdiving,munging,mutha,muthafecker,muthafuckker,muther,mutherfucker,n1gga,n1gger,nambla,nawashi,nazi,negro,neonazi,nig nog,nigaboo,nigg3r,nigg4h,nigga,niggah,niggas,niggaz,nigger,niggers,niglet,nimphomania,nipple,nipples,nob,nob jokey,nobhead,nobjocky,nobjokey,nsfw images,nude,nudity,numbnuts,nut sack,nutsack,nympho,nymphomania,octopussy,omorashi,one cup two girls,one guy one jar,orgasim,orgasims,orgasm,orgasms,orgy,p0rn,paedophile,paki,panooch,panties,panty,pawn,pecker,peckerhead,pedobear,pedophile,pegging,penis,penisbanger,penisfucker,penispuffer,phone sex,phonesex,phuck,phuk,phuked,phuking,phukked,phukking,phuks,phuq,piece of shit,pigfucker,pimpis,piss,piss pig,pissed,pissed off,pisser,pissers,pisses,pissflaps,pissin,pissing,pissoff,pisspig,playboy,pleasure chest,pole smoker,polesmoker,pollock,ponyplay,poof,poon,poonani,poonany,poontang,poop,poop chute,poopchute,poopuncher,porch monkey,porchmonkey,porn,porno,pornography,pornos,prick,pricks,prince albert piercing,pron,pthc,pube,pubes,punanny,punany,punta,pusse,pussi,pussies,pussy,pussylicking,pussys,pust,puto,queaf,queef,queer,queerbait,queerhole,quim,raghead,raging boner,rape,raping,rapist,rectum,renob,retard,reverse cowgirl,rimjaw,rimjob,rimming,rosy palm,rosy palm and her 5 sisters,ruski,rusty trombone,s.o.b.,s&m,sadism,sadist,sand nigger,sandler,sandnigger,sanger,santorum,scat,schlong,scissoring,screwing,scroat,scrote,scrotum,seks,semen,sex,sexo,sexy,shag,shagger,shaggin,shagging,shaved beaver,shaved pussy,shemale,shi+,shibari,shit,shitass,shitbag,shitbagger,shitblimp,shitbrains,shitbreath,shitcanned,shitcunt,shitdick,shite,shited,shitey,shitface,shitfaced,shitfuck,shitfull,shithead,shithole,shithouse,shiting,shitings,shits,shitspitter,shitstain,shitted,shitter,shitters,shittiest,shitting,shittings,shitty,shiz,shiznit,shota,shrimping,skank,skeet,skullfuck,slag,slanteye,slut,slutbag,sluts,smeg,smegma,smut,snatch,snowballing,sodomize,sodomy,son-of-a-bitch,spac,spic,spick,splooge,splooge moose,spooge,spook,spread legs,spunk,strap on,strapon,strappado,strip club,style doggy,suck,suckass,sucks,suicide girls,sultry women,swastika,swinger,t1tt1e5,t1tties,tainted love,tard,taste my,tea bagging,teets,teez,testical,testicle,threesome,throating,thundercunt,tied up,tight white,tit,titfuck,tits,titt,tittie5,tittiefucker,titties,titty,tittyfuck,tittywank,titwank,tongue in a,topless,tosser,towelhead,tranny,tribadism,tub girl,tubgirl,turd,tushy,tw4t,twat,twathead,twatlips,twats,twatty,twatwaffle,twink,twinkie,two girls one cup,twunt,twunter,unclefucker,undressing,upskirt,urethra play,urophilia,v14gra,v1gra,va-j-j,vag,vagina,vajayjay,venus mound,viagra,vibrator,violet wand,vjayjay,vorarephilia,voyeur,vulva,w00se,wang,wank,wanker,wankjob,wanky,wet dream,wetback,white power,whoar,whore,whorebag,whoreface,willies,willy,wop,wrapping men,wrinkled starfish,xrated,xx,xxx,yaoi,yellow showers,yiffy,zoophilia,zubb,a$$,a$$hole,a55hole,ahole,anal impaler,anal leakage,analprobe,ass fuck,ass hole,assbang,assbanged,assbangs,assfaces,assh0le,beatch,bimbo,bitch tit,bitched,bloody hell,bootee,bootie,bull shit,bullshits,bullshitted,bullturds,bum boy,butt fuck,buttfuck,buttmunch,c-0-c-k,c-o-c-k,c-u-n-t,c.0.c.k,c.o.c.k.,c.u.n.t,caca,cacafuego,chi-chi man,child-fucker,clit licker,cock sucker,corksucker,corp whore,crackwhore,dammit,damned,damnit,darn,dick head,dick hole,dick shy,dick-ish,dickdipper,dickflipper,dickheads,dickish,f-u-c-k,f.u.c.k,fist fuck,fuck hole,fuck puppet,fuck trophy,fuck yo mama,fuck you,fuck-ass,fuck-bitch,fuck-tard,fuckedup,fuckmeat,fucknugget,fucktoy,fuq,gassy ass,h0m0,h0mo,ham flap,he-she,hircismus,holy shit,hom0,hoor,jackasses,jackhole,middle finger,moo foo,naked,p.u.s.s.y.,piss off,piss-off,rubbish,s-o-b,s0b,shit ass,shit fucker,shiteater,son of a bitch,son of a whore,two fingers,wh0re,wh0reface";
         String[] badWords = input.split(",");
        
//String[] badWords = {"fuck", "badword2", "badword3"};
 String contenuCCC = contenuC.getText();
         for (String badWord : badWords) {
            Pattern pattern = Pattern.compile("\\b" + badWord + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(contenuCCC);
           contenuCCC = matcher.replaceAll("***");
        }

            
         
            CommentaireCrud rc = new CommentaireCrud();
           rc.AjouterCommantaire(contenuCCC,IdE);
        

    }

    public void setData() {

    }

    @FXML
    private void modifierCC(ActionEvent event) {
         String contenuCCC = contenuC.getText();
         String IDCC = IDC.getText();
            CommentaireCrud rc = new CommentaireCrud();
           rc.ModifierCommentaire(Integer.valueOf(IDCC),contenuCCC, IdE);
    }

    @FXML
    private void suprimerCC(ActionEvent event) {
       
         String IDCC = IDC.getText();
            CommentaireCrud rc = new CommentaireCrud();
           rc.SuprimerCommantaire(Integer.valueOf(IDCC));
    }

      

    
    @FXML
    private void showdemande () {
        
            CommentaireCrud cc = new CommentaireCrud();
        List<Commentaire> myList = cc.AfficherCommentaire(IdE);  
        
       tvC.setCellValueFactory(new PropertyValueFactory<>("idCommentaire"));
       tvCC.setCellValueFactory(new PropertyValueFactory<>("contenu"));
          
     ObservableList<Commentaire> observableList = FXCollections.observableArrayList(myList);
    tableviewC.setItems(observableList);
    }
    
    
  @FXML
    private void affC(ActionEvent event) {
 showdemande ();
    }

    
  
   
    
    @FXML
private void translateCC(ActionEvent event) {
    String mamamia=affCCC.getValue();
    String IDCC = IDC.getText();
     CommentaireCrud cc = new CommentaireCrud();
        List<Commentaire> myList = cc.AfficherCommentaire2(Integer.valueOf(IDCC));
       String text= myList.get(0).getContenu();
       boolean areEqual = mamamia.equals("traduire en arabe");
        boolean areEqual2 = mamamia.equals("traduire en francais");
         boolean areEqual3 = mamamia.equals("traduire en German");
       if (areEqual){
        try {
            String fromLang = "en";
            String toLang = "ar";
          //  String text = "how are you";
          labeltraduction.setText(Tronslator.translate(fromLang, toLang, text));
         
        } catch (Exception ex) {
            Logger.getLogger(DetailWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
              if (areEqual2){
        try {
            String fromLang = "en";
            String toLang = "fr";
          //  String text = "how are you";
          labeltraduction.setText(Tronslator.translate(fromLang, toLang, text));
         
        } catch (Exception ex) {
            Logger.getLogger(DetailWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
                     if (areEqual3){
        try {
            String fromLang = "en";
            String toLang = "de";
          //  String text = "how are you";
          labeltraduction.setText(Tronslator.translate(fromLang, toLang, text));
         
        } catch (Exception ex) {
            Logger.getLogger(DetailWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }




}
    

    
}
