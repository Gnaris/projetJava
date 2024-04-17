package com.school.main.request.ClasseRequest;

import com.school.main.models.Classe;

public abstract class ClasseRequest {
    protected Classe classe;

    public Classe getClasse()
    {
        return this.classe;
    }
}
