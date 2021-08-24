package com.epam.jwd.tetrahedron.observer;

import com.epam.jwd.tetrahedron.model.Tetrahedron;
import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

public interface Subject {

    void registerObserver(TetrahedronRegistrar registrar);

    void removeObserver(TetrahedronRegistrar registrar);

    void notifyObservers(Tetrahedron tetrahedron);
}
