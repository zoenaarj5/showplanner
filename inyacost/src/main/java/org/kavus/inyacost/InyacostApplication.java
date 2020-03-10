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
			LanguageCode.setCurrentLanguageCode(LanguageCode.FR);
			//PERSISTING TRANSLATORS
			final Translator<LTranslation> showTranslators[]=new Translator[]{show1Translator};
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
			System.out.println("««««««««««««««««««««««««««««««««««««««««««««««");
			showRepository.findById(shows[0].getId()).ifPresent(show -> {
				System.out.println("Finally Printing (translated) show... [ORIGINAL]");
				System.out.println(show);
				shows[0]=show;
				System.out.println("Finally Printing (translated) show... [COPY]");
				System.out.println(shows[0]);
			});
			System.out.println("««««««««««««««««««««««««««««««««««««««««««««««");
			final Translator<LTranslation>[][] sceneTranslators=new Translator[][]{{scene11Translator,scene12Translator}};
			final Translation[][][] sceneTranslations=new Translation[][][]{{{sceneTranslation11EN,sceneTranslation11FR},{sceneTranslation12EN,sceneTranslation12FR}}};
			final Scene[][] scenes=new Scene[][]{{scene11,scene12}};
			System.out.println("____________________________________________________________________________________________________________________________________________________________");
			System.out.println("PERSISTING TRANSLATORS + TRANSLATIONS + SCENES");
			for(int i=0;i<sceneTranslators.length;i++){
				final int ii=i;
				for(int j=0;j<sceneTranslators[i].length;j++) {
					final int jj=j;
					System.out.println("\nPersisting translator ID="+sceneTranslators[i][j].getId());
					translatorRepository.save(sceneTranslators[i][j]);
					System.out.println("Translator persisted: ID="+sceneTranslators[i][j].getId());
					translatorRepository.findById(sceneTranslators[i][j].getId()).ifPresent(tran->{
						System.out.println("===================================================");
						System.out.println("\nPrinting translator after persist [ORIGINAL]. . .");
						System.out.println(tran);
						sceneTranslators[ii][jj]=tran;
						System.out.println("\nPrinting translator after persist [COPY]. . .");
						System.out.println(sceneTranslators[ii][jj]);
						System.out.println("===================================================");
						System.out.println("Persisting scene: ID="+scenes[ii][jj].getId());
						scenes[ii][jj].setTranslator(sceneTranslators[ii][jj]);
						sceneTranslators[ii][jj].setTranslatable(scenes[ii][jj]);
						sceneRepository.save(scenes[ii][jj]);
						scenes[ii][jj].setShow(shows[0]);
//						showRepository.save(shows[0]);
						System.out.println("\n\nPrinting scene #"+scenes[ii][jj].getId()+" - after adding show/////////////////////////");
						System.out.println(scenes[ii][jj]);
						System.out.println("\nPrinting shows[0]++++++++++++++++++++++++++++++++++++++++++++++");
						System.out.println(shows[0]);
						System.out.println("\nDONE Printing shows[0]++++++++++++++++++++++++++++++++++++++++++++++");
						sceneRepository.save(scenes[ii][jj]);
						System.out.println("\n\nPersisted scene --> ID="+scenes[ii][jj].getId());
							sceneRepository.findById(scenes[ii][jj].getId()).ifPresent(sc->{
								scenes[ii][jj]=sc;
								sceneRepository.findById(scenes[ii][jj].getId()).ifPresent(sce->{
									System.out.println("********************************************************");
									System.out.println("Printing scene[][] after setting translator + persist [ORIGINAL]");
									System.out.println(sce);
									scenes[ii][jj]=sce;
									System.out.println("Printing scene[][] after setting translator + persist [COPY]");
									System.out.println(scenes[ii][jj]);
									//PERSISTING TRANSLATIONS
									System.out.println("********************************************************");
									System.out.println("Persisting translations");
//									sceneTranslators[ii][jj].addTranslations(sceneTranslations[ii][jj]);
									translatorRepository.save(sceneTranslators[ii][jj]);
									for(int k=0;k<sceneTranslations[ii][jj].length;k++){
										final int kk=k;
										System.out.println("\n\tPersisting translation ################################################\n");
										System.out.println("\n\t\t################ Translation ID BEFORE persist = "+sceneTranslations[ii][jj][k].getId());
										translationRepository.save(sceneTranslations[ii][jj][k]);
										translationRepository.findById(sceneTranslations[ii][jj][k].getId()).ifPresent(stran->{
											sceneTranslations[ii][jj][kk]=stran;
											System.out.println("\n\t\t################ Translation ID AFTER persist = "+sceneTranslations[ii][jj][kk].getId());
											sceneTranslations[ii][jj][kk].setTranslator(sceneTranslators[ii][jj]);
											translationRepository.save(sceneTranslations[ii][jj][kk]);
											translationRepository.findById(sceneTranslations[ii][jj][kk].getId()).ifPresent(str->{
												System.out.println("| | | | | | | | | | | | | | | | | | | | PRINTING TRANSLATION AFTER PERSIST [ORIGINAL]");
												System.out.println(str);
												System.out.println("--- --- --- --- Translator id = "+str.getTranslator().getId());
												System.out.println("| | | | | | | | | | | | | | | | | | | | DONE PRINTING TRANSLATION AFTER PERSIST [original]");
												sceneTranslations[ii][jj][kk]=str;
												System.out.println("| | | | | | | | | | | | | | | | | | | | PRINTING TRANSLATION AFTER PERSIST [COPY]");
												System.out.println(sceneTranslations[ii][jj][kk]);
												System.out.println("--- --- --- --- Translator id = "+sceneTranslations[ii][jj][kk].getTranslator().getId());
												System.out.println("| | | | | | | | | | | | | | | | | | | | DONE PRINTING TRANSLATION AFTER PERSIST [copy]");
											});
										});
									}
								});
							});
						});
					System.out.println("\n...........................PRINTING RESULTING SHOWS AFTER ALL...........................\n");
					showRepository.findAll().forEach(show -> {
						System.out.println("\t\t{{{{{{{{{{{{{{{{{{{{{{{{{{{   Printing SHOW   }}}}}}}}}}}}}}}}}}}}}}}}}}}\n");
						System.out.println(show);
						System.out.println("\t\t{{{{{{{{{{{{{{{{{{{{{{{{{{{ Done printing show }}}}}}}}}}}}}}}}}}}}}}}}}}}\n");
					});
					System.out.println("\n...........................DONE PRINTING RESULTING SHOWS...........................\n");
					System.out.println("\n...........................PRINTING RESULTING SCENES AFTER ALL...........................\n");
					sceneRepository.findAll().forEach(scene -> {
						System.out.println("\t\t{{{{{{{{{{{{{{{{{{{{{{{{{{{   Printing SCENE   }}}}}}}}}}}}}}}}}}}}}}}}}}}\n");
						System.out.println(scene);
						System.out.println("\t\t{{{{{{{{{{{{{{{{{{{{{{{{{{{ Done printing scene }}}}}}}}}}}}}}}}}}}}}}}}}}}\n");
					});
					System.out.println("\n...........................DONE PRINTING RESULTING SCENES...........................\n");
				}
			}
		};
	}
}
