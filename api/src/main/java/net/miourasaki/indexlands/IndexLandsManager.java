/*
 *  Copyright (C) <2024> Miourasaki<mio@chyan.moe>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.miourasaki.indexlands;

import net.miourasaki.indexlands.area.Area;
import net.miourasaki.indexlands.common.Translate;
import net.miourasaki.indexlands.land.Land;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IndexLandsManager {

    Translate getTranslate();
    void setTranslate(Translate translate);

    Land getLand(String landName);
    Land getLand(UUID landID);
    Land getLand(Area landArea);
    void createLand(String landName, Player owenrPlayer);

}
