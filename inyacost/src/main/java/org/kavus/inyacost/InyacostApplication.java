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
		return args->{
			LanguageCode.setCurrentLanguageCode(LanguageCode.FR);
			// ADDING TRANSLATIONS TO SHOW
			final Show[] shows=new Show[]{show1};
			final LTranslation[] showTranslations=new LTranslation[]{showTranslation1EN,showTranslation1FR};
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>SHOW BEFORE PERSIST 1: ID="+shows[0].getId());
			showRepository.save(shows[0]);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>SHOW AFTER PERSIST 1: ID="+shows[0].getId());
			System.out.println(shows[0]);
			lTranslationRepository.saveAll(Arrays.asList(showTranslations));
			shows[0].addTranslations(showTranslations);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>SHOW AFTER PERSIST 2: ID="+shows[0].getId());
			System.out.println(shows[0]);
			final Scene[] scenes=new Scene[]{scene11,scene12};
			final LTranslation[][] sceneTranslations=new LTranslation[][]{{sceneTranslation11EN,sceneTranslation11FR},{sceneTranslation12EN,sceneTranslation12FR}};
			for(int i=0;i<sceneTranslations.length;i++){
				System.out.println("||||||||||||||||||||||||||||||||SCENE BEFORE PERSIST 1: ID="+scenes[i].getId());
				sceneRepository.save(scenes[i]);
				lTranslationRepository.saveAll(Arrays.asList(sceneTranslations[i]));
				System.out.println("||||||||||||||||||||||||||||||||SCENE AFTER PERSIST 1: ID="+scenes[i].getId());
				System.out.println(scenes[i]);
				scenes[i].addTranslations(sceneTranslations[i]);
				System.out.println("||||||||||||||||||||||||||||||||SCENE AFTER PERSIST 2: ID="+scenes[i].getId());
				System.out.println(scenes[i]);
			}
			System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\SHOW BEFORE ADDING SCENES: ID="+shows[0].getId());
			System.out.println(shows[0]);
			shows[0].setScenes(new HashSet<>(Arrays.asList(scenes)));
			showRepository.save(shows[0]);
			System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\SHOW AFTER ADDING SCENES: ID="+shows[0].getId());
			System.out.println(shows[0]);
			showRepository.findById(Long.valueOf(shows[0].getId())).ifPresent(show -> {
				System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\SHOW AFTER ADDING SCENES + PERSIST: ID="+shows[0].getId());
				System.out.println(show);
				shows[0]=show;
			});
		};
	}
}
