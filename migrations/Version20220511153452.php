<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220511153452 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE Comments (id INT AUTO_INCREMENT NOT NULL, news_id INT DEFAULT NULL, text LONGTEXT NOT NULL, comment_date DATE NOT NULL, INDEX IDX_A6E8F47CB5A459A0 (news_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE Likes (id INT AUTO_INCREMENT NOT NULL, comments_id INT DEFAULT NULL, INDEX IDX_880B617963379586 (comments_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE News (id INT AUTO_INCREMENT NOT NULL, newcategoriee_id INT DEFAULT NULL, newcategorie_id INT DEFAULT NULL, title VARCHAR(255) NOT NULL, bg_img VARCHAR(500) DEFAULT NULL, img VARCHAR(500) NOT NULL, description VARCHAR(255) NOT NULL, creation_date DATE NOT NULL, INDEX IDX_BDE1366ED37788E2 (newcategoriee_id), INDEX IDX_BDE1366E7047E4AB (newcategorie_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE news_tag (news_id INT NOT NULL, tag_id INT NOT NULL, INDEX IDX_BE3ED8A1B5A459A0 (news_id), INDEX IDX_BE3ED8A1BAD26311 (tag_id), PRIMARY KEY(news_id, tag_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE newcategorie (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE tag (id INT AUTO_INCREMENT NOT NULL, text VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE Comments ADD CONSTRAINT FK_A6E8F47CB5A459A0 FOREIGN KEY (news_id) REFERENCES News (id)');
        $this->addSql('ALTER TABLE Likes ADD CONSTRAINT FK_880B617963379586 FOREIGN KEY (comments_id) REFERENCES Comments (id)');
        $this->addSql('ALTER TABLE News ADD CONSTRAINT FK_BDE1366ED37788E2 FOREIGN KEY (newcategoriee_id) REFERENCES newcategorie (id)');
        $this->addSql('ALTER TABLE News ADD CONSTRAINT FK_BDE1366E7047E4AB FOREIGN KEY (newcategorie_id) REFERENCES newcategorie (id)');
        $this->addSql('ALTER TABLE news_tag ADD CONSTRAINT FK_BE3ED8A1B5A459A0 FOREIGN KEY (news_id) REFERENCES News (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE news_tag ADD CONSTRAINT FK_BE3ED8A1BAD26311 FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE Likes DROP FOREIGN KEY FK_880B617963379586');
        $this->addSql('ALTER TABLE Comments DROP FOREIGN KEY FK_A6E8F47CB5A459A0');
        $this->addSql('ALTER TABLE news_tag DROP FOREIGN KEY FK_BE3ED8A1B5A459A0');
        $this->addSql('ALTER TABLE News DROP FOREIGN KEY FK_BDE1366ED37788E2');
        $this->addSql('ALTER TABLE News DROP FOREIGN KEY FK_BDE1366E7047E4AB');
        $this->addSql('ALTER TABLE news_tag DROP FOREIGN KEY FK_BE3ED8A1BAD26311');
        $this->addSql('DROP TABLE Comments');
        $this->addSql('DROP TABLE Likes');
        $this->addSql('DROP TABLE News');
        $this->addSql('DROP TABLE news_tag');
        $this->addSql('DROP TABLE newcategorie');
        $this->addSql('DROP TABLE tag');
    }
}