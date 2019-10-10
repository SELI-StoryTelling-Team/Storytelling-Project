-- -------------------------------------------------------------------------
-- PostgreSQL SQL create tables
-- exported at Wed Oct 09 16:09:11 BOT 2019 with easyDesigner
-- -------------------------------------------------------------------------

-- -------------------------------------------------------------------------
-- Table: Object
-- -------------------------------------------------------------------------
CREATE TABLE "Object" (
  "Id_object" bigserial NOT NULL,
  "name" VARCHAR NOT NULL,
  "verified" BOOL NULL,
  "description" VARCHAR NULL,
  PRIMARY KEY ("Id_object")
);

-- -------------------------------------------------------------------------
-- Table: Story
-- -------------------------------------------------------------------------
CREATE TABLE "Story" (
  "id_story" bigserial NOT NULL,
  "name" VARCHAR NULL,
  "description" VARCHAR NULL,
  PRIMARY KEY ("id_story")
);

-- -------------------------------------------------------------------------
-- Table: User
-- -------------------------------------------------------------------------
CREATE TABLE "User" (
  "id_user" bigserial NOT NULL,
  "rol_id_rol" INTEGER NOT NULL,
  "name" VARCHAR NULL,
  "password" VARCHAR NULL,
  "active" BOOL NULL,
  PRIMARY KEY ("id_user")
);

-- -------------------------------------------------------------------------
-- Table: ShareObjet
-- -------------------------------------------------------------------------
CREATE TABLE "ShareObjet" (
  "Object_Id_object" INTEGER NOT NULL,
  "User_id_user" INTEGER NOT NULL,
  "group_id_group" INTEGER NOT NULL,
  "owner" BOOL NULL,
  PRIMARY KEY ("Object_Id_object", "User_id_user")
);

-- -------------------------------------------------------------------------
-- Table: ShareStory
-- -------------------------------------------------------------------------
CREATE TABLE "ShareStory" (
  "Story_id_story" INTEGER NOT NULL,
  "User_id_user" INTEGER NOT NULL,
  "group_id_group" INTEGER NOT NULL,
  "owner" BOOL NULL,
  "default" BOOL NULL,
  PRIMARY KEY ("Story_id_story", "User_id_user")
);

-- -------------------------------------------------------------------------
-- Table: StoryComposition
-- -------------------------------------------------------------------------
CREATE TABLE "StoryComposition" (
  "Object_Id_object" INTEGER NOT NULL,
  "Story_id_story" INTEGER NOT NULL,
  PRIMARY KEY ("Object_Id_object", "Story_id_story")
);

-- -------------------------------------------------------------------------
-- Table: ConcreteObject
-- -------------------------------------------------------------------------
CREATE TABLE "ConcreteObject" (
  "Object_Id_object" INTEGER NOT NULL,
  "data" BLOB NULL,
  PRIMARY KEY ("Object_Id_object")
);

-- -------------------------------------------------------------------------
-- Table: Sesion
-- -------------------------------------------------------------------------
CREATE TABLE "Sesion" (
  "id_sesion" bigserial NOT NULL,
  "User_id_user" INTEGER NOT NULL,
  "pid" INTEGER NOT NULL,
  PRIMARY KEY ("id_sesion", "User_id_user")
);

-- -------------------------------------------------------------------------
-- Table: rol
-- -------------------------------------------------------------------------
CREATE TABLE "rol" (
  "id_rol" bigserial NOT NULL,
  "name" VARCHAR NULL,
  "active" BOOL NULL,
  PRIMARY KEY ("id_rol")
);

-- -------------------------------------------------------------------------
-- Table: userInterface
-- -------------------------------------------------------------------------
CREATE TABLE "userInterface" (
  "id_ui" bigserial NOT NULL,
  "name" VARCHAR NULL,
  "description" VARCHAR NULL,
  "active" BOOL NULL,
  PRIMARY KEY ("id_ui")
);

-- -------------------------------------------------------------------------
-- Table: rol_ui
-- -------------------------------------------------------------------------
CREATE TABLE "rol_ui" (
  "rol_id_rol" INTEGER NOT NULL,
  "userInterface_id_ui" INTEGER NOT NULL,
  "active" BOOL NULL,
  PRIMARY KEY ("rol_id_rol", "userInterface_id_ui")
);

-- -------------------------------------------------------------------------
-- Table: log
-- -------------------------------------------------------------------------
CREATE TABLE "log" (
  "id_log" bigserial NOT NULL,
  "event_id_event" INTEGER NOT NULL,
  "User_id_user" INTEGER NOT NULL,
  "date" DATE NULL,
  "time" TIME NULL,
  "old_data" VARCHAR(255) NULL,
  "new_data" VARCHAR(255) NULL,
  PRIMARY KEY ("id_log")
);

-- -------------------------------------------------------------------------
-- Table: event
-- -------------------------------------------------------------------------
CREATE TABLE "event" (
  "id_event" bigserial NOT NULL,
  "name" VARCHAR NULL,
  PRIMARY KEY ("id_event")
);

-- -------------------------------------------------------------------------
-- Table: group
-- -------------------------------------------------------------------------
CREATE TABLE "group" (
  "id_group" bigserial NOT NULL,
  "name" VARCHAR NULL,
  PRIMARY KEY ("id_group")
);

-- -------------------------------------------------------------------------
-- Table: workshop
-- -------------------------------------------------------------------------
CREATE TABLE "workshop" (
  "group_id_group" INTEGER NOT NULL,
  "User_id_user" INTEGER NOT NULL,
  "active" BOOL NULL,
  PRIMARY KEY ("group_id_group", "User_id_user")
);

-- -------------------------------------------------------------------------
-- Relations for table: User
-- -------------------------------------------------------------------------
ALTER TABLE "User" ADD FOREIGN KEY ("rol_id_rol") 
    REFERENCES "rol" ("id_rol")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: ShareObjet
-- -------------------------------------------------------------------------
ALTER TABLE "ShareObjet" ADD FOREIGN KEY ("Object_Id_object") 
    REFERENCES "Object" ("Id_object")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "ShareObjet" ADD FOREIGN KEY ("User_id_user") 
    REFERENCES "User" ("id_user")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "ShareObjet" ADD FOREIGN KEY ("group_id_group") 
    REFERENCES "group" ("id_group")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: ShareStory
-- -------------------------------------------------------------------------
ALTER TABLE "ShareStory" ADD FOREIGN KEY ("Story_id_story") 
    REFERENCES "Story" ("id_story")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "ShareStory" ADD FOREIGN KEY ("User_id_user") 
    REFERENCES "User" ("id_user")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "ShareStory" ADD FOREIGN KEY ("group_id_group") 
    REFERENCES "group" ("id_group")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: StoryComposition
-- -------------------------------------------------------------------------
ALTER TABLE "StoryComposition" ADD FOREIGN KEY ("Object_Id_object") 
    REFERENCES "Object" ("Id_object")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "StoryComposition" ADD FOREIGN KEY ("Story_id_story") 
    REFERENCES "Story" ("id_story")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: ConcreteObject
-- -------------------------------------------------------------------------
ALTER TABLE "ConcreteObject" ADD FOREIGN KEY ("Object_Id_object") 
    REFERENCES "Object" ("Id_object")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: Sesion
-- -------------------------------------------------------------------------
ALTER TABLE "Sesion" ADD FOREIGN KEY ("User_id_user") 
    REFERENCES "User" ("id_user")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: rol_ui
-- -------------------------------------------------------------------------
ALTER TABLE "rol_ui" ADD FOREIGN KEY ("rol_id_rol") 
    REFERENCES "rol" ("id_rol")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "rol_ui" ADD FOREIGN KEY ("userInterface_id_ui") 
    REFERENCES "userInterface" ("id_ui")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: log
-- -------------------------------------------------------------------------
ALTER TABLE "log" ADD FOREIGN KEY ("User_id_user") 
    REFERENCES "User" ("id_user")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "log" ADD FOREIGN KEY ("event_id_event") 
    REFERENCES "event" ("id_event")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

-- -------------------------------------------------------------------------
-- Relations for table: workshop
-- -------------------------------------------------------------------------
ALTER TABLE "workshop" ADD FOREIGN KEY ("group_id_group") 
    REFERENCES "group" ("id_group")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;
ALTER TABLE "workshop" ADD FOREIGN KEY ("User_id_user") 
    REFERENCES "User" ("id_user")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;

