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
			LanguageCode.setCurrentLanguageCode(LanguageCode.FR);
			//PERSISTING CONTENT
			//Persisting shows
			final Show showSaved[]=new Show[1];
			showSaved[0]=showRepository.save(show1);
			System.out.println("Saved:");
			System.out.println(showSaved[0]);
			//Persisting translations
			final LTranslation[]
				trnzSho1=new LTranslation[]{showTranslation1EN,showTranslation1FR},
				trnzSce11=new LTranslation[]{sceneTranslation11EN,sceneTranslation11FR},
				trnzSce12=new LTranslation[]{sceneTranslation12EN,sceneTranslation12FR},
				trnzSce11Saved=new LTranslation[trnzSce11.length],
				trnzSce12Saved=new LTranslation[trnzSce12.length];
			final LTranslation[][]
					trnzSce=new LTranslation[][]{trnzSce11,trnzSce12},
					trnzSceSaved=new LTranslation[][]{trnzSce11Saved,trnzSce12Saved};
			for(int i=0;i<trnzSce.length;i++) {
				for (int j = 0; j < trnzSce[i].length; j++) {
//					trnzSceSaved[i][j] = lTranslationRepository.save(trnzSce[i][j]);
					System.out.println(trnzSceSaved[i][j]);
				}
			}
			System.out.println("length "+trnzSceSaved.length+"width "+trnzSceSaved[0].length);
			//Persisting scenes
//			scene11.addTranslations(trnzSce11Saved);
//			scene12.addTranslations(trnzSce12Saved);
			final Scene[]
				scenes=new Scene[]{scene11,scene12},
				scenesSaved=new Scene[scenes.length];
			for(int i=0;i<scenes.length;i++){
				scenesSaved[i]=sceneRepository.save(scenes[i]);
				System.out.println("Saved now:\n"+scenesSaved[i]);
			}
			//PRINTING CONTENT
			//Printing translations
			System.out.println("End of program");
		};
	}
}
