package org.kavus.inyacost;

import org.aspectj.weaver.ltw.LTWWorld;
import org.kavus.inyacost.ntt.*;
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
		showTranslation1EN=new LTranslation(LanguageCode.EN,show1,"Miseke and Nkuba","Adventure of Miseke, the Thunder's bride","The story of a girl who was promised by her parents to Prince Thunder. Out of a popular Rwandan fairy tale."),
		showTranslation1FR=new LTranslation(LanguageCode.FR,show1,"Miseke et Nkuba","Aventures de Miseke, la fiancée de la Foudre","L'histoire de la fille promise par ses parents au Prince Foudre. Provient d'un conte populaire Rwandais."),
		sceneTranslation11EN=new LTranslation(LanguageCode.EN,scene11,"Thunder helps Miseke´s mum","The beginning of the tale: Nkuba helps Miseke's mother to carry water","Miseke's mother, while pregnant of her, cannot carry water she just fetched. And then..."),
		getSceneTranslation11FR=new LTranslation(LanguageCode.FR,scene11,"La Foudre aide la mère de Miseke","Le début de l'histoire: Nkuba aide la mère de Miseke à puiser de l'eau","La mère de Miseke, enceinte d'elle, peine à porter l'eau qu'elle vient de puiser. Et puis..."),
		sceneTranslation12EN=new LTranslation(LanguageCode.EN,scene12,"Thunder asks for Miseke","Thunder reaches for the first time to ask for his promised bride","Miseke is now grown up, almost adolescent. Nkuba comes to get her but her parents refuse because she's still too young."),
		scenTranslation12FR=new LTranslation(LanguageCode.FR,scene12,"La Foudre demande Miseke","La Foudre arrive pour la première fois demander la main de Miseke","Miseke est à présent grande, presque adolescente. Nkuba arrive pour la prendre, mais ses parents refusent car elle est encore trop jeune.")
	;

	public static void main(String[] args) {
		SpringApplication.run(InyacostApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(ShowRepository showRepository, SceneRepository sceneRepository, TranslationRepository translationRepository){
		return args -> {
			//SETTING LANGUAGE CODE
			LanguageCode.setCurrentLanguageCode(LanguageCode.FR);

			//PERSISTING CONTENT
			//Persisting scenes
			final Scene[] scnz=new Scene[]{scene11,scene12},
					scnzSaved=new Scene[scnz.length];
			for(int i=0;i<scnz.length;i++) {
				scnzSaved[i]=sceneRepository.save(scnz[i]);
//				scnzSaved[i].setShow(show1Saved[0]);
				scnzSaved[i]=sceneRepository.save(scnzSaved[i]);
			}
			final Show[] show1Saved=new Show[1];
			show1Saved[0]=showRepository.save(show1);
			show1Saved[0].addTranslations(new LTranslation[]{showTranslation1EN,showTranslation1FR});
			show1Saved[0].setScenes(new HashSet<>(Arrays.asList(scnzSaved)));
			LTranslation[]
				showTranz=new LTranslation[]{showTranslation1FR,showTranslation1EN},
				showTranzSaved=new LTranslation[showTranz.length];
			for(int i=0;i<showTranz.length;i++){
				showTranz[i].setTranslatable(show1Saved[0]);
				showTranzSaved[i]=translationRepository.save(showTranz[i]);
			}
			showRepository.findById(Long.valueOf(show1Saved[0].getId())).ifPresent(show -> {
				show1Saved[0]=show;
			});
			show1Saved[0].setScenes(new HashSet<>(Arrays.asList(new Scene[]{scene11,scene12})));
			showRepository.save(show1Saved[0]);
			showRepository.findById(Long.valueOf(show1Saved[0].getId())).ifPresent(show -> {
				show1Saved[0]=show;
			});
			//PRINTING CONTENT AFTER SAVING
			System.out.println("PRINTING SHOWS...");
			showRepository.findAll().forEach(show -> {
				System.out.println(show);
			});
			System.out.println("PRINTING SCENES...");
			sceneRepository.findAll().forEach(scene -> {
				System.out.println(scene);
			});
		};
	}
}
