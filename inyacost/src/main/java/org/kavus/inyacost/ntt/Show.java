package org.kavus.inyacost.ntt;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Show extends Translatable<LTranslation>{
    @OneToMany(targetEntity = Scene.class,mappedBy = "show",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected Set<Scene> scenes;

    public Show() {
        super();
    }

    public Set<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(Set<Scene> scenes) {
        this.scenes = scenes;
    }

    public Show(Set<Scene> scenes) {
        this.scenes = scenes;
    }
    @Override
    public String toString(){
        final StringBuilder sb=new StringBuilder(super.toString());
        if(scenes!=null && !scenes.isEmpty()) {
            sb.append("\nScenes included:");
            scenes.forEach(scene -> {
                sb.append("\n-\t");
                LTranslation sTran=scene.getTranslator().getTranslation();
                sb.append(sTran==null?("#"+scene.getId()):sTran.getTitle());
            });
        }else{
            sb.append("\nNo scenes available.");
        }
        return sb.toString();
    }
}
