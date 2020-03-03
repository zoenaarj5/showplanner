package org.kavus.inyacost.ntt;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Translatable <T extends Translation>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @OneToMany(mappedBy = "translatable",targetEntity = Translation.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    protected Set<T> translationSet;
    @Transient
    protected T translation;
    public long getId() {
        return id;
    }
    public Translatable() {
        super();
    }

    public Translatable(Set<T> translationSet) {
        this.translationSet = translationSet;
        updateTranslation();
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<T> getTranslationSet() {
        return translationSet;
    }

    public void setTranslationSet(Set<T> translationSet) {
        this.translationSet = translationSet;
        updateTranslation();
    }
    public T getTranslation() {
        return translation;
    }

    public void setTranslation(T translation) {
        this.translation = translation;
    }
    public void updateTranslation(){
        final LanguageCode clc=LanguageCode.getCurrentLanguageCode();
        if(translationSet!=null){
            for(T trans:translationSet){
                LanguageCode lgc=trans.getLanguageCode();
                if(lgc==clc){
                    System.out.println("Matching language code! "+lgc+" = "+clc);
                    setTranslation(trans);
                    break;
                }else{
                    System.out.println("Not corresponding language code:"+lgc+" <> "+clc);
                }
                System.out.println("Translation is now: "+translation);
            }
        }
    }
    public void addTranslations(T[] translations){
        if(translationSet==null){
            translationSet=new HashSet<>();
        }
        final Object[] lcObjz=translationSet.stream().map(t->t.getLanguageCode()).toArray();
        final Set<T> toRemoveSet=new HashSet<>();
        for (T trans : translationSet) {
            for(Object lcObj:lcObjz) {
                LanguageCode lc=(LanguageCode)lcObj;
                if (trans.getLanguageCode() == lc) {
                    toRemoveSet.add(trans);
                }
            }
        }
        translationSet.removeAll(toRemoveSet);
        translationSet.addAll(Arrays.asList(translations));
        System.out.println("Added "+translations.length+" translation(s).\n");
        updateTranslation();
    }
    public void addTranslation(T translation){
        if(translationSet==null){
            translationSet=new HashSet<>();
        }
        final Set<T> toRemoveSet=new HashSet<>();
        for (T trans : translationSet) {
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
        StringBuilder sb=new StringBuilder(">>>TRANS ID="+id+"\n");
        sb.append(translation!=null?""+translationSet.size()+" available translations. Here's the current:\n"+translation.toString():"No translations available");
        return sb.toString();
    }
}
