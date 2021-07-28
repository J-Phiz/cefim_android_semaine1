package fr.jpsave.android.movieapp.constants;

import java.util.Date;

import fr.jpsave.android.movieapp.R;
import fr.jpsave.android.movieapp.model.Movie;

public class StaticMovies {

    public static Movie FillStarWars() {
        Movie movie = new Movie();

        movie.setTitle("Star Wars Episode IV - A New Hope");
        movie.setReleaseDate("25 Mai 1977");
        movie.setGenre("Action, Adventure, Fantasy, Sci-Fi");
        movie.setDirector("George Lucas");
        movie.setActors("Mark Hamill, Harisson Ford, Carrie Fisher, Peter Cushing");
        movie.setAwards("Won 6 Oscars. Another 52 wins and 28 nominations.");
        movie.setDescription(
           "L'univers de Star Wars se déroule dans une galaxie qui est le théâtre d'affrontements entre les Chevaliers Jedi et les Seigneurs noirs des Sith, personnes sensibles à la Force, un champ énergétique mystérieux leur procurant des pouvoirs psychiques. Les Jedi maîtrisent le Côté lumineux de la Force, pouvoir bénéfique et défensif, pour maintenir la paix dans la galaxie. Les Sith utilisent le Côté obscur, pouvoir nuisible et destructeur, pour leurs usages personnels et pour dominer la galaxie1." +
           "Pour ramener la paix, une République galactique a été fondée avec pour capitale la planète Coruscant. Mais, tout au long de son existence, elle est secouée par des sécessions et des guerres. Pour y mettre fin, la République est remplacée en 19 av. BYNote 2 par un Empire galactique autoritaire et discriminatoire. Cette nouvelle entité est dirigée par le Sith Palpatine, son apprenti Dark Vador et son chef d'état-major Tarkin2." +
           "Cependant, après plusieurs années, la brutalité du régime provoque l'apparition d'une opposition armée : l'Alliance rebelle. Le premier coup d'éclat de cette dernière se déroule quelque temps avant BYNote 3, lorsque des espions rebelles parviennent à mettre la main sur les plans de la station spatiale Étoile noire, une arme absolue que Tarkin construit en secret depuis l’avènement de l’Empire. La princesse Leia, l'une des responsables de l'Alliance, est alors chargée de rapporter en lieu sûr les plans dérobésa 1."
        );
        movie.setImageId(R.drawable.star_wars_ep4);

        return movie;
    }

    public static Movie FillBatman() {
        Movie movie = new Movie();

        movie.setTitle("Batman");
        movie.setReleaseDate("12 Juin 1992");
        movie.setGenre("Action, Adventure, Fantasy, Super-Heros");
        movie.setDirector("Tim Burton");
        movie.setActors("Michael Keaton, Danny DeVito, Michelle Pfeiffer, Christopher Walken");
        movie.setAwards("");
        movie.setDescription(
            "À Gotham City, un couple fortuné, Tucker et Esther Cobblepot (Paul Reubens et Diane Salinger), abandonne son enfant à la naissance en le jetant dans les égouts à cause de sa difformité. Il est recueilli et élevé par les manchots du zoo. Trente-trois ans plus tard, Oswald Cobblepot (Danny DeVito) a grandi dans les égouts et refait surface comme un criminel nommé Le Pingouin. Il kidnappe un industriel millionnaire cruel, sadique et égoïste, Max Shreck (Christopher Walken). À cause des preuves rassemblées par le Pingouin des activités criminelles des affaires de Shreck, ce dernier lui propose de le sortir des égouts et de le faire entrer dans l'élite de Gotham. Le Pingouin élabore un plan pour faire son entrée dans le monde public en se faisant passer pour un héros. Il fait kidnapper le fils du maire pour ensuite le délivrer lui-même. Malgré la popularité du Pingouin, le millionnaire Bruce Wayne, alias Batman (Michael Keaton), reste sceptique sur ce dernier. Il enquête sur le passé du Pingouin et établit un lien avec un gang de criminels, le Gang du Cirque du Triangle Rouge. Le gang a récemment fait des ravages sur Gotham, entrainant la disparition de plusieurs enfants. Il décide de défendre Gotham contre eux." +
            "Pendant ce temps, Shreck surprend sa secrétaire Selina Kyle (Michelle Pfeiffer), dans le cadre de sa préparation pour sa rencontre avec Bruce concernant la centrale électrique de Schrek. Tout en regardant innocemment des documents, Selina accède aux fichiers protégés de Shreck et découvre des informations compromettantes, révélant que sa centrale rejette des déchets toxiques. Réalisant que Selina en sait trop, Shreck la pousse d'une fenêtre, la laissant pour morte sur le sol. Selina survit à la chute après avoir été ramenée à la vie par des chats de gouttière. Elle retourne à son appartement, puis elle est victime d'une crise de psychose et s'invente un personnage, Catwoman, une cambrioleuse vêtue d'un costume de chatte en vinyle cherchant à se venger de son patron. Shreck élabore alors un plan pour évincer l'actuel maire de Gotham City (Michael Murphy) et faire élire le Pingouin à sa place, afin de renforcer son contrôle sur la ville et pour finaliser son projet de centrale électrique. Le Gang du Cirque du Triangle Rouge met Gotham à feu et à sang pour créer un climat d'insécurité mauvais pour la réélection du maire actuel." +
            "Pendant ce temps, Bruce et Selina se rencontrent en personne et entament une relation amoureuse sans connaître l'un l'autre leurs doubles-vies, une situation compliquée par le fait que Catwoman, le Pingouin et son gang veulent débarrasser Gotham de Batman. Ils enlèvent la princesse de glace (Cristi Conaway), une femme choisie pour allumer les lumières de l'arbre de Noël de Gotham, et ils tentent de rendre Batman responsable du crime. La princesse de glace se retrouve en équilibre sur le rebord du sommet d'un bâtiment, Batman tente de la sauver, mais en vain, le Pingouin libère de son parapluie plein de chauves-souris qui effrayent la princesse, qui tombe dans le vide et se tue. Lorsque Catwoman rejette ses avances, le Pingouin tente, sans succès, de la tuer. Pendant ce temps, Batman revient à la Batmobile et il constate que le Pingouin a saboté le véhicule. En effet, le Pingouin dirige la Batmobile au travers d'une télécommande dans Gotham. Il jubile car il fait commettre à la Batmobile plein de dégâts dans toute la ville. Cependant, Batman échappe finalement au contrôle du Pingouin en détruisant le tracer électronique." +
            "Quand Bruce expose les plans du Pingouin visant à duper Gotham, en diffusant des paroles enregistrées lors d'un meeting électoral, ruinant ainsi ses chances d'être élu, le Pingouin élabore un plan pour assassiner tous les premiers-nés masculins de Gotham en les enlevant, de les amener dans son antre et de les noyer dans une piscine d'eau contaminée par les déchets toxiques de Shreck. Il tente de prendre personnellement le fils de Max, Charles « Chip » Shreck (Andrew Bryniarski), mais après que Max l'a supplié de le prendre à la place de son fils, le Pingouin accepte. Batman déjoue les plans du Pingouin. Après quoi celui-ci décide de lancer des missiles autour de Gotham en utilisant des manchots sous contrôle mental. Cependant, Batman réussit à bloquer la fréquence utilisée pour contrôler les animaux et lance les missiles sur la base de Cobblepot. Enfin, Batman se confronte au Pingouin. Ce dernier est battu et chute dans les eaux toxiques de sa tanière. Batman tente de persuader Catwoman de livrer Shreck à la police. Pour la convaincre, il va jusqu'à se démasquer, mais Shreck sort une arme à feu et tire sur Batman puis sur Catwoman. Cette dernière affirme qu'elle a encore six de ses neuf vies, et reste encore en vie après que Shreck lui a tiré dessus quatre fois. Finalement, Catwoman utilise un taser pour électrocuter Shreck et provoque une explosion qui tue finalement le monstre impitoyable, mais elle disparaît. Le Pingouin émerge alors de l'eau toxique et tente de tuer Batman, mais il échoue et succombe à ses blessures. Ses manchots emmènent alors son corps dans les eaux des égouts comme tombeau."
        );
        movie.setImageId(R.drawable.batman_1992);

        return movie;
    }

}
