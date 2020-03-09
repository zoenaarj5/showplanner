package org.kavus.inyacost;

import org.kavus.inyacost.ntt.*;
import org.kavus.inyacost.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class InyacostApplication {
	protected static final Translator<LTranslation>
			show1Translator=new Translator<>(),
			scene11Translator=new Translator<>(),
			scene12Translator=new Translator<>();
	protected static final Show show1=new Show();
	protected static final Scene scene11=new Scene();
	protected static final Scene scene12=new Scene();
	protected static final Translation
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
	public CommandLineRunner demo(ShowRepository showRepository, SceneRepository sceneRepository, TranslationRepository translationRepository, TranslatorRepository translatorRepository){
		return args -> {
			//PERSISTING TRANSLATORS
			final Translator<Translation> showTranslators[]=new Translator[]{show1Translator};
			translatorRepository.save(showTranslators[0]);
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			translatorRepository.findById(showTranslators[0].getId()).ifPresent(translator -> {
				System.out.println("Printing show's translator [ORIGINAL]");
				System.out.println(translator);
				showTranslators[0]=translator;
				System.out.println("Printing show's translator [COPY]");
				System.out.println(showTranslators[0]);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			//PERSISTING SHOW TRANSLATIONS
			final Translation[] showTranslations=new Translation[]{showTranslation1EN,showTranslation1FR};
			translationRepository.saveAll(Arrays.asList(showTranslations));
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("Printing show's translations after persist + fetch");
			translationRepository.findAll().forEach(lt->{
				System.out.println(lt);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			//PERSISTING SHOWS
			final Show shows[]=new Show[]{show1};
			shows[0].setTranslator(show1Translator);
			showRepository.save(shows[0]);
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			showRepository.findById(show1.getId()).ifPresent(sh->{
				System.out.println("Printing show after persist + fetch [ORIGINAL]");
				System.out.println(sh);
				shows[0]=sh;
				System.out.println("Printing show after persist + fetch [COPY]");
				System.out.println(shows[0]);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			for(int i=0;i<showTranslations.length;i++){
				showTranslations[i].setTranslator(showTranslators[0]);
				translationRepository.save(showTranslations[i]);
				final int ii=i;
				translationRepository.findById(showTranslations[i].getId()).ifPresent(str->{
					System.out.println("Printing translation after setting translator + persist + fetch [ORIGINAL]");
					System.out.println(str);
					showTranslations[ii]=str;
					System.out.println("Printing translation after setting translator + persist + fetch [COPY]");
					System.out.println(showTranslations[ii]);
				});
			}


//			translationRepository.saveAll(Arrays.asList(showTranslations));
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("Adding translations . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
//			showTranslators[0].addTranslations(showTranslations);
			System.out.println("Persisting translator after adding translations . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
			translatorRepository.save(showTranslators[0]);
			System.out.println("Done adding translations + persisting. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			translatorRepository.findById(showTranslators[0].getId()).ifPresent(t->{
//			translatorRepository.findById(showTranslators[0].getId()).ifPresent(t->{
				System.out.println("Printing fetched translator after adding translations + persist + fetch [ORIGINAL]");
				System.out.println(t);
				showTranslators[0]=t;
				System.out.println("Printing fetched translator after adding translations + persist + fetch [COPY]");
				System.out.println(showTranslators[0]);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
		};
	}
/*		@Bean
		public CommandLineRunner demo(ShowRepository showRepository, SceneRepository sceneRepository, TranslationRepository translationRepository, TranslatorRepository translatorRepository){
		return args->{
			LanguageCode.setCurrentLanguageCode(LanguageCode.FR);
			//PERSISTING TRANSLATORS
			final Translator<Translation>[] showTranslators=new Translator[]{show1Translator};
			translatorRepository.save(showTranslators[0]);
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~PRINTING SHOW TRANSLATOR AFTER PERSIST - BEFORE FETCH");
			translatorRepository.findAll().forEach(translator -> {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~PRINTING SHOW TRANSLATOR AFTER PERSIST - AFTER FETCH");
				System.out.println(translator);
			});
			// ADDING TRANSLATIONS TO SHOW
			final Show[] shows=new Show[]{show1};
			final Translation[] showTranslations=new Translation[]{showTranslation1EN,showTranslation1FR};
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>SHOW BEFORE PERSIST 1: ID="+shows[0].getId());
			showRepository.save(shows[0]);
			showRepository.findById(shows[0].getId()).ifPresent(show -> {
				System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>SHOW AFTER PERSIST 1 [ORIGINAL]: ID="+show.getId());
				System.out.println(show);
				System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>SHOW AFTER PERSIST 1 [COPY]: ID="+shows[0].getId());
				System.out.println(shows[0]);
			});
			shows[0].setTranslator(show1Translator);
			showRepository.save(shows[0]);
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("\n++++++++++++++++++++SHOW AFTER ADDING TRANSLATOR - AFTER PERSIST - BEFORE FETCH");

			System.out.println(shows[0]);
			showRepository.findById(shows[0].getId()).ifPresent(show -> {
				shows[0]=show;
				System.out.println("\n++++++++++++++++++++SHOW AFTER ADDING TRANSLATOR - AFTER PERSIST [ORIGINAL]- AFTER FETCH");
				System.out.println(show);
				System.out.println("\n++++++++++++++++++++SHOW AFTER ADDING TRANSLATOR - AFTER PERSIST [COPY]- AFTER FETCH");
				System.out.println(shows[0]);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			translationRepository.saveAll(Arrays.asList(showTranslations));
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("\nvvvvvvvvvvvvvvvvv PRINTING SHOW TRANSLATIONS AFTER PERSIST 1 - BEFORE FETCH");
			Arrays.stream(showTranslations).forEach(lt->System.out.print(""+lt.getId()+"["+lt.getLanguageCode()+"],"));
			translationRepository.findAll().forEach(t->{
				System.out.println("\nXXXXXXXXXXXXXXXXXXXXX PRINTING SHOW TRANSLATION AFTER PERSIST 1 - AFTER FETCH: ID="+t.getId()+"["+t.getLanguageCode()+"]");
				System.out.println(t);
			});
			shows[0].getTranslator().addTranslations(showTranslations);
			//Printing show after adding translations to show
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("\n>>> >>> >>> PRINTING SHOW AFTER ADDING TRANSLATIONS - BEFORE PERSIST 2 <<< <<< <<<\n");
			System.out.println(shows[0]);
			Arrays.stream(showTranslations).forEach(t->{
				System.out.println("\nTranslation to add to show. . . . . . . . . . .");
				System.out.println(t);
				System.out.println("\nAdding translation to add to show. . . . . . . . . . .");
				shows[0].getTranslator().addTranslation(t);
				System.out.println("\nShow(as translatable) set for translation:\t"+t.getTranslatable()+". . . . . . . . . . .");
				translationRepository.save(t);
			});
//			showRepository.save(shows[0]);
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("\n>>> >>> >>> PRINTING SHOW AFTER ADDING TRANSLATIONS - AFTER PERSIST 2 - BEFORE FETCH <<< <<< <<<\n");
			System.out.println(shows[0]);
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			showRepository.findById(shows[0].getId()).ifPresent(show -> {
				System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>SHOW AFTER ADDING TRANSLATIONS - AFTER PERSIST 2 - AFTER FETCH [ORIGINAL]: ID="+show.getId());
				System.out.println(show);
				shows[0]=show;
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>SHOW AFTER ADDING TRANSLATIONS - AFTER PERSIST 2 - AFTER FETCH [COPY]: ID="+shows[0].getId());
				System.out.println(shows[0]);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");

			final Scene[] scenes=new Scene[]{scene11,scene12};
			final Translation[][] sceneTranslations=new Translation[][]{{sceneTranslation11EN,sceneTranslation11FR},{sceneTranslation12EN,sceneTranslation12FR}};
			for(int i=0;i<sceneTranslations.length;i++){
				System.out.println("||||||||||||||||||||||||||||||||SCENE BEFORE PERSIST 1: ID="+scenes[i].getId());
				sceneRepository.save(scenes[i]);
				translationRepository.saveAll(Arrays.asList(sceneTranslations[i]));
				System.out.println("||||||||||||||||||||||||||||||||SCENE AFTER PERSIST 1: ID="+scenes[i].getId());
				System.out.println(scenes[i]);
				scenes[i].getTranslator().addTranslations(sceneTranslations[i]);
				System.out.println("||||||||||||||||||||||||||||||||SCENE AFTER PERSIST 2: ID="+scenes[i].getId());
				System.out.println(scenes[i]);
			}
			System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\SHOW BEFORE ADDING SCENES: ID="+shows[0].getId());
			System.out.println(shows[0]);
			shows[0].setScenes(new HashSet<>(Arrays.asList(scenes)));
			for(Scene scene:scenes){
				scene.setShow(shows[0]);
				sceneRepository.save(scene);
			}
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			sceneRepository.findAll().forEach(scene -> {
				System.out.print("\n...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...  ...");
				System.out.println("Printing scene after fetch: ID="+scene.getId());
				System.out.println(scene);
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
//			showRepository.save(shows[0]);
			System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\SHOW AFTER ADDING SCENES: ID="+shows[0].getId());
			System.out.println(shows[0]);
			showRepository.findById(Long.valueOf(shows[0].getId())).ifPresent(show -> {
				System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\SHOW AFTER ADDING SCENES + PERSIST: ID="+shows[0].getId());
				System.out.println(show);
				shows[0]=show;
			});
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("END OF PROGRAM");
		};
	}

 */
}
