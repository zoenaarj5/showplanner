package org.kavus.inyacost.ntt;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Translator<T extends Translation> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @OneToOne(targetEntity = Translatable.class,mappedBy = "translator",cascade = CascadeType.ALL)
    protected Translatable<T> translatable;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "translator",targetEntity = Translation.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected Set<Translation> translationSet;
    @Transient
    protected T translation;

    public Translatable<T> getTranslatable() {
        return translatable;
    }

    public void setTranslatable(Translatable<T> translatable) {
        this.translatable = translatable;
    }

    public Set<Translation> getTranslationSet() {
        return translationSet;
    }

    public void setTranslationSet(Set<Translation> translationSet) {
        this.translationSet = translationSet;
        updateTranslation();
    }
    public T getTranslation() {
        return translation;
    }

    public Translator() {
        super();
    }

    public void setTranslation(T translation) {
        this.translation = translation;
    }
    public void updateTranslation() throws ClassCastException{
        final LanguageCode clc=LanguageCode.getCurrentLanguageCode();
        if(translationSet!=null){
            for(Translation trans:translationSet){
                LanguageCode lgc=trans.getLanguageCode();
                if(lgc==clc){
                    System.out.println("Matching language code! "+lgc+" = "+clc);
                    setTranslation((T)trans);
                    break;
                }else{
                    System.out.println("Not corresponding language code:"+lgc+" <> "+clc);
                }
            }
            System.out.println("Translation is now: "+translation);
        }
    }
    public void addTranslations(Translation[] translations){
        if(translationSet==null){
            translationSet=new HashSet<>();
        }
        final Object[] lcObjz=translationSet.stream().map(t->t.getLanguageCode()).toArray();
        final Set<Translation> toRemoveSet=new HashSet<>();
        for (Translation trans : translationSet) {
            for(Object lcObj:lcObjz) {
                LanguageCode lc=(LanguageCode)lcObj;
                if (trans.getLanguageCode() == lc) {
                    toRemoveSet.add(trans);
                }
            }
        }
        translationSet.removeAll(toRemoveSet);
        translationSet.addAll(Arrays.asList(translations));
        updateTranslation();
    }
    public void addTranslation(Translation translation){
        if(translationSet==null){
            translationSet=new HashSet<>();
        }
        final Set<Translation> toRemoveSet=new HashSet<>();
        for (Translation trans : translationSet) {
            if(trans.getLanguageCode()==translation.getLanguageCode()){
                toRemoveSet.add(trans);
            }
        }
        translationSet.removeAll(toRemoveSet);
        translationSet.add(translation);
        System.out.println("Added translation:.......................................\n");
        System.out.println(translation);
        System.out.println("........................................................\n");
        updateTranslation();
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder(">>>TRANSLATOR ID="+id+"\n");
        System.out.println("Updating translation...");
        updateTranslation();
        System.out.println("Translation updated.");
        sb.append((translationSet==null || translationSet.isEmpty())
            ?"No translations available"
            :(""+translationSet.size()+" - Available translations:\n"+translationSet.toString()+"\nCurrent translation: "
                +(translation==null?"None":(""+translation.getId()+"["+translation.getLanguageCode()+"]"))
            )
        );
        return sb.toString();
    }
}
