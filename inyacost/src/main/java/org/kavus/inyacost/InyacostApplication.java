package org.kavus.inyacost;

import org.aspectj.weaver.ltw.LTWWorld;
import org.kavus.inyacost.ntt.*;
import org.kavus.inyacost.repo.LTranslationRepository;
import org.kavus.inyacost.repo.SceneRepository;
import org.kavus.inyacost.repo.ShowRepository;
import org.kavus.inyacost.repo.TranslationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class InyacostApplication {
	protected static final Show show1=new Show();
	protected static final Scene scene11=new Scene();
	protected static final Scene scene12=new Scene();
	protected static final LTranslation
		showTranslation1EN=new LTranslation(LanguageCode.EN,null,"Miseke and Nkuba","Adventure of Miseke, the Thunder's bride","The story of a girl who was promised by her parents to Prince Thunder. Out of a popular Rwandan fairy tale."),
		showTranslation1FR=new LTranslation(LanguageCode.FR,null,"Miseke et Nkuba","Aventures de Miseke, la fiancée de la Foudre","L'histoire de la fille promise par ses parents au Prince Foudre. Provient d'un conte populaire Rwandais."),
		sceneTranslation11EN=new LTranslation(LanguageCode.EN,null,"Thunder helps Miseke´s mum","The beginning of the tale: Nkuba helps Miseke's mother to carry water","Miseke's mother, while pregnant of her, cannot carry water she just fetched. And then..."),
		sceneTranslation11FR=new LTranslation(LanguageCode.FR,null,"La Foudre aide la mère de Miseke","Le début de l'histoire: Nkuba aide la mère de Miseke à puiser de l'eau","La mère de Miseke, enceinte d'elle, peine à porter l'eau qu'elle vient de puiser. Et puis..."),
		sceneTranslation12EN=new LTranslation(LanguageCode.EN,null,"Thunder asks for Miseke","Thunder reaches for the first time to ask for his promised bride","Miseke is now grown up, almost adolescent. Nkuba comes to get her but her parents refuse because she's still too young."),
		sceneTranslation12FR=new LTranslation(LanguageCode.FR,null,"La Foudre demande Miseke","La Foudre arrive pour la première fois demander la main de Miseke","Miseke est à présent grande, presque adolescente. Nkuba arrive pour la prendre, mais ses parents refusent car elle est encore trop jeune.")
	;

	public static void main(String[] args) {
		SpringApplication.run(InyacostApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(ShowRepository showRepository, SceneRepository sceneRepository, LTranslationRepository lTranslationRepository){
		return args -> {
			//SETTING LANGUAGE CODE
			LanguageCode.setCurrentLanguageCode(LanguageCode.EN);
			//PERSISTING CONTENT
			//Persisting shows
			final Show showsSaved[]=new Show[1];
			showsSaved[0]=showRepository.save(show1);
			System.out.println("\nPRINTING PERSISTED SHOW(S)\n");
			System.out.println("sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~");
			System.out.println("Saved show now:");
			System.out.println(showsSaved[0]);
			System.out.println("sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~sho~~~");
			//Persisting translations
			//Persisting scenes
//			scene11.addTranslations(trnzSce11Saved);
//			scene12.addTranslations(trnzSce12Saved);
			final Scene[] scenesSaved=new Scene[]{scene11,scene12};
			System.out.println("\nPRINTING PERSISTED SCENE(S)\n");
			for(int i=0;i<scenesSaved.length;i++){
//				scenesSaved[i].setShow(showSaved[0]);
				sceneRepository.save(scenesSaved[i]);
				System.out.println("scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===");
				System.out.println("Saved scene now:\n"+scenesSaved[i]);
				System.out.println("scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===scn===");
			}
			final LTranslation[]
				trnzSho1Saved=new LTranslation[]{showTranslation1EN,showTranslation1FR},
				trnzSce11Saved=new LTranslation[]{sceneTranslation11EN,sceneTranslation11FR},
				trnzSce12Saved=new LTranslation[]{sceneTranslation12EN,sceneTranslation12FR};
			final LTranslation[][]
					trnzSceSaved=new LTranslation[][]{trnzSce11Saved,trnzSce12Saved};
			System.out.println("\nPRINTING PERSISTED TRANSLATION(S)\n");
			for(int i=0;i<trnzSceSaved.length;i++) {
				for (int j = 0; j < trnzSceSaved[i].length; j++) {
					trnzSceSaved[i][j] = lTranslationRepository.save(trnzSceSaved[i][j]);
					System.out.println("trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\");
					System.out.println("Saved translation now: \n"+trnzSceSaved[i][j]);
					System.out.println("trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\trn\\\\\\");
				}
			}
			System.out.println("length "+trnzSceSaved.length+"width "+trnzSceSaved[0].length);
			System.out.println("\nADDING TRANSLATIONS TO SCENES + PERSISTING SCENES\n");
			for(int i=0;i<trnzSceSaved.length;i++) {
				for (int j = 0; j < trnzSceSaved[i].length; j++) {
//					trnzSceSaved[i][j].setTranslatable(scenesSaved[i]);
//					trnzSceSaved[i][j] = lTranslationRepository.save(trnzSceSaved[i][j]);
					scenesSaved[i].addTranslation(trnzSceSaved[i][j]);
				}
				scenesSaved[i].setShow(showsSaved[0]);
				sceneRepository.save(scenesSaved[i]);
				System.out.println("After adding translations, the scene scenesSaved["+i+"] is now:........................................");
				System.out.println(scenesSaved[i]);
				final int ii=i;
				sceneRepository.findById(Long.valueOf(scenesSaved[i].getId())).ifPresent(scene -> {
					scenesSaved[ii] = scene;
					System.out.println("\nFound scene(ii="+ii+"):\n");
					System.out.println(scene);
				});
			}
//			System.out.println("PERSISTING SCENES AFTER ADDING TRANSLATIONS ?");
			//PRINTING CONTENT
			//Printing scenes
			System.out.println("AND NOW THE SCEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEENEEESSS!!!!!!!!!!!!!!");
			sceneRepository.findAll().forEach(scene -> {
				System.out.println(scene);
			});
			System.out.println("DONE WITH THE SCEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEENEEESSS............");
//			System.out.println("PERSISTING SHOWS AFTER ADDING TRANSLATIONS ?");
			showsSaved[0].addTranslations(trnzSho1Saved);
			showRepository.save(showsSaved[0]);
			Arrays.stream(scenesSaved).forEach(scene -> {
				scene.setShow(showsSaved[0]);
				sceneRepository.save(scene);
			});
			showRepository.findById(Long.valueOf(showsSaved[0].getId())).ifPresent(show -> {
				showsSaved[0]=show;
				System.out.println("######################################");
				System.out.println("Found show #"+show.getId());
				System.out.println(showsSaved[0]);
				System.out.println("######################################");
			});
			System.out.println("AND NOW THE SHOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOWWWS!!!!!!!!!!!!!!");
			showRepository.findAll().forEach(show -> {
				System.out.println(show);
			});
			System.out.println("DONE WITH THE SHOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOWWWS!!!!!!!!!!!!!!");
			System.out.println("End of program");
		};
	}
}
