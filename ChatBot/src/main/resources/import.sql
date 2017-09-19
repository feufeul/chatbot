insert into Functions (name, is_active, short_description, signature) values('!cmdlist',1, 'Cette commande permet de lister les commandes actives', 'cmdlist');
insert into functions (name, is_active, short_description, signature) values('!event',1,'Crée un nouvel événement au format [!event (Description de l événement) YYYY MM DD HH mm]','!event (Description de l événement) YYYY MM DD HH mm');
insert into functions (name, is_active, short_description, signature) values('!eventlist',1,'Permet de lister les événements futurs','!eventlist');
insert into functions (name, is_active, short_description, signature) values('!man',1,'Affiche une description de la commande au format [!man !cmd_recherchée]','!man !cmd_recherchée');
insert into functions (name, is_active, short_description, signature) values('!mod',1,'Permet de transformer un chatter en modérateur','!mod chatter_name');
insert into functions (name, is_active, short_description, signature) values('!participate',1,'Permet de participer à un événementau format [!participate id_événement]','!participate event_id');
insert into functions (name, is_active, short_description, signature) values('!unmod',1,'Permet de retirer les droits de modérateur à un chatter','!unmod chatter_name');
