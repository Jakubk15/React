/*------------------------------------------------------------------------------
 -   Adapt is a Skill/Integration plugin  for Minecraft Bukkit Servers
 -   Copyright (c) 2022 Arcane Arts (Volmit Software)
 -
 -   This program is free software: you can redistribute it and/or modify
 -   it under the terms of the GNU General Public License as published by
 -   the Free Software Foundation, either version 3 of the License, or
 -   (at your option) any later version.
 -
 -   This program is distributed in the hope that it will be useful,
 -   but WITHOUT ANY WARRANTY; without even the implied warranty of
 -   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 -   GNU General Public License for more details.
 -
 -   You should have received a copy of the GNU General Public License
 -   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 -----------------------------------------------------------------------------*/

package com.volmit.react.util.scheduling;

import com.volmit.react.util.math.M;

public interface Ticked {
    default void retick() {
        burst(1);
    }

    default void skip() {
        skip(1);
    }

    void unregister();

    boolean isBursting();

    boolean isSkipping();

    void stopBursting();

    void stopSkipping();

    long getTickCount();

    long getAge();

    void burst(int ticks);

    void skip(int ticks);

    long getTlastTick();

    long getTinterval();

    void setTinterval(long ms);

    void tick();

    String getTgroup();

    String getTid();

    default boolean shouldTick() {
        return M.ms() - getTlastTick() > getTinterval();
    }
}
