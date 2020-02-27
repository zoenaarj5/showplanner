package org.kavus.inyakost;

import org.kavus.inyakost.ntt.*;
import org.kavus.inyakost.repo.DefinitionRepository;
import org.kavus.inyakost.repo.MemberRepository;
import org.kavus.inyakost.repo.SceneRepository;
import org.kavus.inyakost.repo.ShowRepository;
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

	protected static final LDefinition
			showDef1EN=new LDefinition(LanguageCode.EN,"Miseke and Nkuba","The Thunder's bride","Miseke, who was promised to - and married - the Thunder"),
			showDef1FR=new LDefinition(LanguageCode.EN,"Miseke et Nkuba","La fiancée de la Foudre","Miseke, qui fut promise à - et épousa - la foudre"),
			showDef2EN=new LDefinition(LanguageCode.EN,"Ndabaga the heroin","The girl who replaced her father","The story of Ndabaga, ther girl who went to fight at the battlefield for her father to come back home."),
			showDef2FR=new LDefinition(LanguageCode.FR,"Ndabaga l'héroïne","La fille qui releva son père","L'histoire de Ndabaga, la fille qui alla au champ de bataille remplacer son père pour qu'il puisse rentrer chez lui."),
			sceneDef11EN=new LDefinition(LanguageCode.EN,"Miseke's Mom","Nkuba helps Miseke's mother to carry water",""),
			sceneDef11FR=new LDefinition(LanguageCode.FR,"La mère de Miseke","Nkuba aide la mère de Miseke à porter de l'eau","BLABLABLA"),
			sceneDef12EN=new LDefinition(LanguageCode.EN,"Nkuba asks for Miseke","Nkuba asks for his promised bride","BLABLABLA"),
			sceneDef12FR=new LDefinition(LanguageCode.FR,"Nkuba demande Miseke","Nkuba demande la fille qui lui est promise",""),
			sceneDef21EN=new LDefinition(LanguageCode.EN,"Ndabaga is born","Ndabaga's father is called to the battle right after she was born","BLABLABLA"),
			sceneDef21FR=new LDefinition(LanguageCode.FR,"Ndabaga est née","Le père de Ndabaga est appelé au combat juste après sa naissance","BLABLABLA"),
			sceneDef22EN=new LDefinition(LanguageCode.EN,"Ndabaga grows up","Ndabaga learns to fight so she can replace her father","BLABLABLA"),
			sceneDef22FR=new LDefinition(LanguageCode.FR,"Ndabaga grandit","Ndabaga apprend à se battre pour pouvoir aller remplacer son père","BLABLABLA")
	;
	protected static final Scene scene11=new Scene(new HashSet<Definition>(Arrays.asList(new Definition[]{sceneDef11EN,sceneDef11FR})),null,10);
	protected static final Scene scene12=new Scene(new HashSet<Definition>(Arrays.asList(new Definition[]{sceneDef12EN,sceneDef12FR})),null,5);
	protected static final Scene scene21=new Scene(new HashSet<Definition>(Arrays.asList(new Definition[]{sceneDef21EN,sceneDef21FR})),null,8);
	protected static final Scene scene22=new Scene(new HashSet<Definition>(Arrays.asList(new Definition[]{sceneDef22EN,sceneDef22FR})),null,4);
	protected static final Show show1=new Show(new HashSet<Definition>(Arrays.asList(new Definition[]{showDef1EN,showDef1FR})),LocalDate.of(2007,10,4),new HashSet<Scene>(Arrays.asList(new Scene[]{scene11,scene12})),100);
	protected static final Show show2=new Show(new HashSet<Definition>(Arrays.asList(new Definition[] {showDef2EN,showDef2FR})),LocalDate.of(2007,10,4),new HashSet<Scene>(Arrays.asList(new Scene[]{scene21,scene22})),130);
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
	public CommandLineRunner demoDefinition(DefinitionRepository repository){
		return args->{
			for(Definition definition:new Definition[]{
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
	}
	@Bean
	public CommandLineRunner demoScene(SceneRepository repository){
		return args -> {
			for(Scene scene:new Scene[]{scene11,scene12,scene21,scene22}){
				repository.save(scene);
			}
			System.out.println("=============PRINTING SCENES=============");
			for(Scene scene:repository.findAll()){
				scene.updateDefinition();
				System.out.println(scene);
			}
		};
	}
	@Bean
	public CommandLineRunner demoShow(ShowRepository repository){
		return args->{
			for(Show show:new Show[]{show1,show2}){
				repository.save(show);
			}
			System.out.println("=============PRINTING SHOWS=============");
			for(Show show:repository.findAll()){
				show.updateDefinition();
				System.out.println(show);
			}
		};
	}
}
