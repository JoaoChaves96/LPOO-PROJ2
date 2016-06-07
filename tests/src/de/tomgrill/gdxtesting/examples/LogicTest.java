/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package de.tomgrill.gdxtesting.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.lpoo.game.Logic.Enemy;
import com.lpoo.game.Logic.Hero;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class LogicTest {

	@Test
	public void oneEqualsOne() {
		assertEquals(1, 1);
	}

	@Test
	public void testHeroMove(){
		assertTrue(Gdx.files.internal("hero.png").exists());

		for (int i = 1; i<4; i++){
			Hero hero = new Hero(50, 50, i);

			hero.update(0.1f);

			hero.moveUp();
			assertEquals(55, (int)hero.getPositionY());
			assertEquals(50, (int)hero.getPositionX());
			hero.moveDown();
			assertEquals(50, (int)hero.getPositionY());
			assertEquals(50, (int)hero.getPositionX());
			hero.moveRight();
			assertEquals(50, (int)hero.getPositionY());
			assertEquals(55, (int)hero.getPositionX());
			hero.moveLeft();
			assertEquals(50, (int)hero.getPositionY());
			assertEquals(50, (int)hero.getPositionX());
		}
	}

	@Test
	public void testCollisions(){
		Hero hero = new Hero(50, 50, 1);
		Enemy en = new Enemy(50, 50);
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(en);

		hero.checkCollisions(enemies);

		assertEquals(true, hero.isDead());
	}

	@Test
	public void testHerogetHitandLives(){
		Hero hero = new Hero(50, 50, 1);
		hero.getHit(20);
		assertEquals(80, hero.getHealth());
		assertFalse(hero.isDead());
		hero.getHit(80);
		assertTrue(hero.isDead());
	}

	@Test
	public void testHeroDisposed(){
		Hero hero = new Hero(50, 50, 1);
	}

}
