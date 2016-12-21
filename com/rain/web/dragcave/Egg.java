package com.rain.web.dragcave;

import java.util.Date;

public class Egg {
	private Date dateAppeared;	//when
	private String type;		//what
	private int location;		//where
	private String id;			//who
	private String description;
	
	public Egg(String des, Date date, int loc, String id) {
		this.type = this.parseDescription(des);
		this.description = des;
		this.dateAppeared = date;
		this.location = loc;
		this.id = id;
	}
//super
	@Override
	public String toString(){
		return location + " " + type + " : " + id + " : " + dateAppeared.toString() + " : " + description;
	}
	
//non-private access/mutators
	//access
	public Date getDateAppeared(){ return dateAppeared; }
	
	public int getLocation(){ return location; }
	
	public String getType(){ return type; }
	
	public String getId(){ return id; }
	
//private mutators	
	private String parseDescription(String d){
		if("It’s almost like time is distorted around this egg.".contains(d))
			return "Aeon Wyvern";
		else if(d.contains("it is brightly colored with"))
			return "Dino";
		else if("You can see the baby dragon curled up inside this translucent egg.".contains(d))
			return "Albino";
		else if("This glossy green egg is rather warm.".contains(d))
			return "Almerald";
		else if("This delicately patterned egg is sitting in the sunshine.".contains(d))
			return "Anagallis";
		else if("This egg is surrounded by mysterious, reflective dust.".contains(d))
			return "Antarean";
		else if("This bright egg has a warm shell.".contains(d))
			return "Aria";
		else if("This iridescent egg radiates mysterious energy.".contains(d))
			return "Avatar of Change";
		else if("This shimmering egg radiates primordial energy.".contains(d))
			return "Avatar of Creation";
		else if("This smoldering egg radiates volatile energy.".contains(d))
			return "Avatar of Destruction";
		else if("This light egg is floating in the air.".contains(d)	)
			return "Balloon";
		else if("This egg has a faint green glow around it.".contains(d))
			return "Black";
		else if("This egg has a black cap.".contains(d))
			return "Black Capped Teimarr";
		else if("This egg has a faintly exotic scent.".contains(d))
			return "Black Tea";
		else if("This egg is off-white in color and smells a bit like salt.".contains(d))
			return "Blacktip";
		else if("This egg shines brilliantly in moonlight, and is covered in red spots.".contains(d))
			return "Bleeding Moon";
		else if("This egg is covered in thick blue stripes.".contains(d))
			return "Blue-Banded";
		else if("This egg looks like it doesn't belong; it is brightly colored with white spots. It's much lighter than the other eggs.".contains(d))
			return "Blue Dino/Purple Dino";
		else if("This egg smells faintly like brine.".contains(d))
			return "Blusang Lindwurm";
		else if("This egg has a striking pattern.".contains(d))
			return "Bolt";
		else if("This egg is covered in speckles.".contains(d))
			return "Bright-Breasted Wyvern";
		else if("This egg smells rather rancid.".contains(d))
			return "Brimstone";
		else if("This egg is unusually large and heavy.".contains(d))
			return "Brute";
		else if("This mottled red egg almost glows from within.".contains(d))
			return "Candelabra";
		else if("This egg is hidden by some leaves.".contains(d))
			return "Canopy";
		else if("This pink and red egg wobbles occasionally.".contains(d))
			return "Carmine Wyvern";
		else if("This egg makes you feel a bit uneasy.".contains(d))
			return "Cassare";
		else if("This translucent egg shines like starlight.".contains(d))
			return "Celestial";
		else if("This egg is soft and smells uncannily like cheese.".contains(d))
			return "Cheese";
		else if("This egg is much smaller than the others.".contains(d))
			return "Chicken";
		else if("This egg reminds you of the sea.".contains(d))
			return "Coastal Waverunner";
		else if("This egg gleams with a reddish shine.".contains(d))
			return "Copper";
		else if("This egg is tiny and brightly colored.".contains(d))
			return "Crimson Flare Pygmy";
		else if("This egg is sitting in a patch of grass and small flowers even though there's no sun in the cave.      ".contains(d) || d.contains("This egg is sitting in a patch of grass and small flowers even though there") )
			return "Dark Green";
		else if("This egg shines coldly in the moonlight.".contains(d))
			return "Dark Lumina";
		else if("This tiny egg is mysterious and dark.".contains(d))
			return "Dark Myst Pygmy";
		else if("This egg is sitting on a cloud.".contains(d))
			return "Daydream";
		else if("This egg displays the colors of both dawn and dusk.".contains(d))
			return "Day/Night Glory Drake";
		else if("This egg appears to be covered in scales.".contains(d)) //could be one of two possible
			return "Deep Sea/Shallow Water";
		else if("This egg shines like a diamond.".contains(d))
			return "Diamondwing";
		else if("This egg has multiple bands of color on it.".contains(d))
			return "Dorsal";
		else if("This egg can’t seem to make up its mind on what color it is.".contains(d))
			return "Duotone";
		else if("	Electric sparks dance across the surface of this egg.".contains(d))
			return "Electric";
		else if("This egg is really hot.".contains(d))
			return "Ember";
		else if("Cold flames dance across the surface of this egg.".contains(d))
			return "Falconiform Wyvern";
		else if("This egg is patterned with an orange flare.".contains(d))
			return "Fever Wyvern";
		else if(d.contains("It's brigh")) //coudl be one of two possible
			return "Flamingo Wyvern/Pink";
		else if(d.contains("It's bright And Pink"))
			return "Flamingo Wyvern/Pink";
		else if("Frost is creeping over this cold egg.".contains(d))
			return "Frostbite";
		else if("This egg is encrusted with colorful gemstones.".contains(d))
			return "Gemshard";
		else if("This egg is heavy and rough, as if it were made out of rock.".contains(d)) //could be one of two
			return "Geode/Stone";
		else if("This egg glitters oddly in the light.".contains(d))
			return "Gilded Bloodscale";
		else if("This striped egg feels moist.".contains(d))
			return "Glaucus Drake";
		else if("This egg is very reflective, almost metallic-looking.".contains(d))
			return "Gold";
		else if("This egg has bright orange and green markings.".contains(d))
			return "Gold-Horned Tangar";
		else if("This egg shimmers like gold.".contains(d))
			return "Golden Wyvern";
		else if("This egg is speckled with rosette-like markings.".contains(d))
			return "Greater Spotted Drake";
		else if("This egg looks like it doesn't belong; it is brightly colored with white spots.".contains(d))
			return "Green Dino";
		else if("This egg is sitting in a pile of small pebbles.".contains(d))
			return "Green";
		else if("This egg is sitting in front of the others.".contains(d))
			return "Guardian";
		else if("This egg glows mysteriously.".contains(d))
			return "Guardian of Nature";
		else if("This heavy egg feels slightly warm.".contains(d))
			return "Harvest";
		else if("This egg radiates the heat of a fell flame.".contains(d))
			return "Hellfire Wyvern";
		else if("This hot egg shakes violently when you touch it.".contains(d))
			return "Hellhorse";
		else if("The surface of this egg is rough and sharp.".contains(d))
			return "Hooktalon";
		else if("This egg has strange markings on it.".contains(d))
			return "Horse/Frilled/Skywing/Ochredrake";
		else if("You hear strange noises coming from inside this egg.".contains(d))
			return "Howler Drake";
		else if("This egg has icicles forming on it.".contains(d))
			return "Ice";
		else if("This egg is hidden in the trees.".contains(d))
			return "Imperial Fleshcrowne";
		else if("This shiny egg gives off an almost magical aura.".contains(d))
			return "Kingcrowne";
		else if("This tiny cobalt egg has a crystalline sheen.".contains(d))
			return "Kyanite Pygmy";
		else if("This egg shines brightly in the sunlight.".contains(d))
			return "Lumina";
		else if("This metallic egg shows faint iridescence in moonlight.".contains(d))
			return "Lunar Herald";
		else if("This small egg is engulfed in a cool, purple flame.".contains(d))
			return "Magelight Pygmy";
		else if("This egg has an orange aura radiating from it.".contains(d))
			return "Magi";
		else if("This egg is almost too hot to touch.".contains(d))
			return "Magma";
		else if("This egg is hidden behind the others, as if it is shy.".contains(d))
			return "Mint";
		else if("This tiny egg has crazy swirls on it.".contains(d))
			return "Misfit Pygmy";
		else if("This egg is buried in leaf litter.".contains(d))
			return "Monarch";
		else if("This egg resembles a glowing stone.".contains(d))
			return "Moonstone/Sunstone";
		else if("A few bright markings decorate this egg’s shell.".contains(d))
			return "Morphodrake";
		else if("This egg has a brilliant radiance coming off of it.".contains(d))
			return "Nebula";
		else if("This egg is very sickly looking, like it's diseased.".contains(d))
			return "Neglected";
		else if("This egg has strange yellow stripes.".contains(d))
			return "Neotropical";
		else if("This dense, crystalline egg seems dangerously unstable.".contains(d))
			return "Nexus";
		else if("	This dull purple egg has two bright stripes on it.".contains(d))
			return "Nhiostrife Wyvern";
		else if("This tiny egg is heavier than you expected.	".contains(d))
			return "Nilia Pygmy";
		else if("This egg appears to be made of limestone.".contains(d))
			return "Nocturne";
		else if("This egg smells musty, like rotting leaves.".contains(d))
			return "Olive";
		else if("This egg is tiny and made out of several pieces of paper folded together.".contains(d))
			return "Paper";
		else if("This egg has a velvety texture.".contains(d))
			return "Pillow";
		else if("This massive egg is covered with thick plates.".contains(d))
			return "Plated Colossus";
		else if("Wow, purple isn’t a color of egg you expected to see.".contains(d))
			return "Purple";
		else if("This egg is so tiny you almost didn't see it.    ".contains(d) || d.contains("This egg is so tiny you almost didn"))
			return "Pygmy";
		else if("This egg feels like polished stone.".contains(d))
			return "Pyralspite";
		else if("This egg looks like it doesn't belong; it is brightly colored with white spots. It's much warmer than the rest of the eggs.".contains(d))
			return "Red Dino";
		else if("This egg is rather warm.".contains(d))
			return "Red";
		else if("This egg is wet from the waves and has bright red stripes.".contains(d))
			return "Red-finned Tidal";
		else if("A cool mountain breeze blows around this egg.".contains(d))
			return "Ridgewing";
		else if("This egg shines in the moonlight.".contains(d))
			return "Royal Blue";
		else if("This large egg is a dark crimson color.".contains(d))
			return "Royal Crimson";
		else if("This egg looks like a beautiful blue stone.".contains(d))
			return "Sapphire";
		else if("The markings on this egg match the weather outside.".contains(d))
			return "Seasonal";
		else if("This tiny egg shines like a pearl.".contains(d))
			return "Seawyrm Pygmy";
		else if("This plain-looking egg has faint speckles.".contains(d))
			return "Seragamma Wyvern";
		else if("This egg is very reflective, almost metallic looking.".contains(d))
			return "Shimmer-scale";
		else if("This egg gives off a beautiful glow.".contains(d))
			return "Siver/Soulpeace";
		else if("This egg has a rough—yet shiny—shell.".contains(d))
			return "Speckle-Throated";
		else if("This egg glows from within.".contains(d))
			return "Spirit Ward";
		else if("This egg has brightly colored markings on it.".contains(d))
			return "Spitfire/Striped";
		else if("This egg is covered in bright spots.".contains(d))
			return "Spotted Greenwing";
		else if("This egg is surrounded by fog.".contains(d))
			return "Storm";
		else if("You can feel the static electricity that surrounds this egg.".contains(d))
			return "Storm-Rider";
		else if("A small puddle of condensation has collected under this egg.".contains(d))
			return "Striped River";
		else if("This egg is glowing as brightly as the sun.".contains(d))
			return "Sunset/Sunrise";
		else if("This egg changes colors in the sunlight.".contains(d))
			return "Sunsong Amphiptere";
		else if("This egg has a very thin shell.".contains(d))
			return "Swallowtail";
		else if("A delicate web-like pattern decorates this egg’s shell.".contains(d))
			return "Tarantula Hawk Drake";
		else if("This egg is a lush green hue.".contains(d))
			return "Terrae";
		else if("Whenever you go near this egg, your hair stands on end.".contains(d))
			return "Thunder";
		else if("This brown egg is covered in intricate designs.".contains(d))
			return "Tri-Horn Wyvern";
		else if("This egg is rocking back and forth in a puddle, creating small waves.".contains(d))
			return "Tsunami Wyvern";
		else if("This egg smells strongly of turpentine.".contains(d))
			return "Turpentine";
		else if("This egg has colored speckles on it.".contains(d))
			return "Two-Finned Bluna";
		else if("This egg is split down the middle into two colors.".contains(d))
			return "Two-Headed";
		else if("This opalescent egg shimmers in the moonlight.".contains(d))
			return "Two-headed Lindwurm";
		else if("This egg is very warm, as if it has been sitting out in strong sunlight.".contains(d))
			return "Ultraviolet";
		else if("There is a thin layer of moisture coating this egg.".contains(d))
			return "Undine";
		else if("This egg is stone cold and smells rotten.".contains(d))
			return "Vampire";
		else if("This egg is sitting in a shallow puddle.".contains(d))
			return "Water";
		else if("This egg seems to be floating on a puddle.".contains(d))
			return "Water Walker";
		else if("This egg is slimy and blue.".contains(d))
			return "Waterhorse";
		else if("This egg shakes from time to time, as if it is eager to hatch.".contains(d))
			return "Whiptail";
		else if("This egg has a very clean look; it's completely devoid of dirt and scratches.  ".contains(d) || d.contains("This egg has a very clean look; it"))
			return "White";
		else if("Mana courses throughout this glassy egg.".contains(d))
			return "Xenowyrm";
		else if("This egg looks like it doesn't belong; it is brightly colored with white spots. It's much heavier than the other eggs.".contains(d))
			return "Yellow Dino";
		else if("The air shimmers around this egg, as if from heat.".contains(d))
			return "Yellow-Crowned";
		else if("This egg is covered in tiny golden scales.".contains(d))
			return "Honey";
		else if("This large egg has a lustrous sheen and appears to be covered in scales.".contains(d))
			return "Azure Glacewing";
		else if("This mottled egg looks positively ancient.".contains(d))
			return "Fell";
		else if(d.contains("Oh my"))
			return "Leetle";
			
				
		

		else return "Unknown";
	}

}
