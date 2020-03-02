package org.kavus.inyakost;

import org.kavus.inyakost.ntt.*;
import org.kavus.inyakost.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class InyakostApplication {
	protected static final PersonalData
			pd1=new PersonalData("Valentin","Kavuna", LocalDate.of(1980,8,19), Sex.M),
			pd2=new PersonalData("Kim","Basinger",LocalDate.of(1953,12,8), Sex.F);
	protected static final SigninData
			sd1=new SigninData("kavuna","Kavuna1","kacva@yahoo.com"),
			sd2=new SigninData("kimbasinger","Kim1","kimbasinger@hollywood.com");
	protected static final Member member1=new Member(pd1,sd1),
									member2=new Member(pd2,sd2);
	protected static final Show
			show1=new Show(null,LocalDate.of(2007,10,4),null,100),
			show2=new Show(null,LocalDate.of(2007,10,4),null,130)
	;
	protected static final Scene
		scene11=new Scene(null,show1,10),
		scene12=new Scene(null,show1,5),
		scene21=new Scene(null,show2,8),
		scene22=new Scene(null,show2,4)
	;
	protected static Definer<LDefinition>
		show1Definer=new Definer(),
		show2Definer=new Definer(),
		scene11Definer=new Definer(),
		scene12Definer=new Definer(),
		scene21Definer=new Definer(),
		scene22Definer=new Definer()
	;
	protected static final LDefinition
		showDefs[]=new LDefinition[]{
			new LDefinition(LanguageCode.EN, show1Definer, "Miseke and Nkuba", "The Thunder's bride", "Miseke, who was promised to - and married - the Thunder"),
			new LDefinition(LanguageCode.EN, show1Definer, "Miseke et Nkuba", "La fiancée de la Foudre", "Miseke, qui fut promise à - et épousa - la foudre"),
			new LDefinition(LanguageCode.EN, show2Definer, "Ndabaga the heroin", "The girl who replaced her father", "The story of Ndabaga, ther girl who went to fight at the battlefield for her father to come back home."),
			new LDefinition(LanguageCode.FR, show2Definer, "Ndabaga l'héroïne", "La fille qui releva son père", "L'histoire de Ndabaga, la fille qui alla au champ de bataille remplacer son père pour qu'il puisse rentrer chez lui.")
		},
		sceneDefs[]=new LDefinition[]{
			new LDefinition(LanguageCode.EN, scene11Definer, "Miseke's Mom", "Nkuba helps Miseke's mother to carry water", ""),
			new LDefinition(LanguageCode.FR, scene11Definer, "La mère de Miseke", "Nkuba aide la mère de Miseke à porter de l'eau", "BLABLABLA"),
			new LDefinition(LanguageCode.EN, scene12Definer, "Nkuba asks for Miseke", "Nkuba asks for his promised bride", "BLABLABLA"),
			new LDefinition(LanguageCode.FR, scene12Definer, "Nkuba demande Miseke", "Nkuba demande la fille qui lui est promise", ""),
			new LDefinition(LanguageCode.EN, scene21Definer, "Ndabaga is born", "Ndabaga's father is called to the battle right after she was born", "BLABLABLA"),
			new LDefinition(LanguageCode.FR, scene21Definer, "Ndabaga est née", "Le père de Ndabaga est appelé au combat juste après sa naissance", "BLABLABLA"),
			new LDefinition(LanguageCode.EN, scene22Definer, "Ndabaga grows up", "Ndabaga learns to fight so she can replace her father", "BLABLABLA"),
			new LDefinition(LanguageCode.FR, scene22Definer, "Ndabaga grandit", "Ndabaga apprend à se battre pour pouvoir aller remplacer son père", "BLABLABLA")
		}
	;
	public static void main(String[] args) {
		SpringApplication.run(InyakostApplication.class, args);
	}
	@Bean
	public CommandLineRunner demoMember(MemberRepository repository){
		return args->{
			for(Member member:new Member[]{member1,member2}){
				repository.save(member);
			}
			System.out.println("=============PRINTING MEMBERS=============");
			for(Member member:repository.findAll()){
				System.out.println(member);
			}
		};
	}
	@Bean
	public CommandLineRunner demoShow(ShowRepository showRepository,SceneRepository sceneRepository,DefinerRepository definerRepository,LDefinitionRepository lDefinitionRepository){

		return args->{
			definerRepository.saveAll(new HashSet<>(Arrays.asList(new Definer[]{show1Definer,show2Definer,scene11Definer,scene22Definer,scene12Definer,scene22Definer})));
			show1.setSceneSet(new HashSet<>(Arrays.asList(new Scene[]{scene11,scene12})));
			show2.setSceneSet(new HashSet<>(Arrays.asList(new Scene[]{scene21,scene22})));
			showRepository.saveAll(Arrays.asList(new Show[]{show1,show2}));
			lDefinitionRepository.saveAll(Arrays.asList(showDefs));
			System.out.println("=============PRINTING SHOWS=============");
			for(Show show:showRepository.findAll()){
				show.updateDefinition();
				System.out.println(show);
			}
/*			sceneRepository.saveAll(Arrays.asList(new Scene[]{scene11,scene12,scene21,scene22}));
			lDefinitionRepository.saveAll(Arrays.asList(sceneDefs));
			System.out.println("=============PRINTING SCENES=============");
			for(Scene scene:sceneRepository.findAll()){
				scene.updateDefinition();
				System.out.println(scene);
			}
*/		};
	}
	@Bean
	public CommandLineRunner demoScene(SceneRepository repository){
		return args -> {
		};
	}
/*	@Bean
	public CommandLineRunner demoDefinition(LDefinitionRepository repository){
		return args->{
			for(LDefinition definition:new LDefinition[]{
					showDef1EN,showDef1FR,showDef2EN,showDef2FR,
					sceneDef11EN,sceneDef11FR,sceneDef12EN,sceneDef12FR,
					sceneDef21EN,sceneDef21FR,sceneDef22EN,sceneDef22FR}){
				repository.save(definition);
			}
			System.out.println("=============PRINTING DEFINITIONS=============");
			for(Definition definition:repository.findAll()){
				System.out.println(definition);
			}
		};
	}*/
}
