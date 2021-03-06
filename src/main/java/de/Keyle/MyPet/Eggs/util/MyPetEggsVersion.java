/*
 * This file is part of MyPet-NPC
 *
 * Copyright (C) 2011-2013 Keyle
 * MyPet-NPC is licensed under the GNU Lesser General Public License.
 *
 * MyPet-NPC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyPet-NPC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyPet.Eggs.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class MyPetEggsVersion {
    private static boolean updated = false;

    private static String myPetNpcVersion = "0.0.0";
    private static String myPetNpcBuild = "0";
    private static String requiredMyPetBuild = "0";

    private static void getManifestVersion() {
        try {
            String path = MyPetEggsVersion.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            Attributes attr = getClassLoaderForExtraModule(path).getMainAttributes();

            if (attr.getValue("Project-Version") != null) {
                myPetNpcVersion = attr.getValue("Project-Version");
            }
            if (attr.getValue("Project-Build") != null) {
                myPetNpcBuild = attr.getValue("Project-Build");
            }
            if (attr.getValue("Required-MyPet-Build") != null) {
                requiredMyPetBuild = attr.getValue("Required-MyPet-Build");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Manifest getClassLoaderForExtraModule(String filepath) throws IOException {
        File jar = new File(filepath);
        JarFile jf = new JarFile(jar);
        Manifest mf = jf.getManifest();
        jf.close();
        return mf;

    }

    public static String getMyPetEggsVersion() {
        if (!updated) {
            getManifestVersion();
            updated = true;
        }
        return myPetNpcVersion;
    }

    public static String getMyPetEggsBuild() {
        if (!updated) {
            getManifestVersion();
            updated = true;
        }
        return myPetNpcBuild;
    }

    public static String getRequiredMyPetBuild() {
        if (!updated) {
            getManifestVersion();
            updated = true;
        }
        return requiredMyPetBuild;
    }

    public static void reset() {
        updated = false;
    }
}