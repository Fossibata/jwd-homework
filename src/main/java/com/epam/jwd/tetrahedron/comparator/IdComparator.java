package com.epam.jwd.tetrahedron.comparator;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

import java.util.Comparator;

public class IdComparator implements Comparator<TetrahedronRegistrar> {
    @Override
    public int compare(TetrahedronRegistrar o1, TetrahedronRegistrar o2) {
        return o1.getId() - o2.getId();
    }
}
