<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220506191427 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE comments DROP FOREIGN KEY FK_A6E8F47CB5A459A0');
        $this->addSql('DROP INDEX IDX_A6E8F47CB5A459A0 ON comments');
        $this->addSql('ALTER TABLE comments DROP news_id');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7D63379586');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_880B617963379586 FOREIGN KEY (comments_id) REFERENCES Comments (id)');
        $this->addSql('ALTER TABLE news_tag DROP FOREIGN KEY FK_BE3ED8A1BAD26311');
        $this->addSql('ALTER TABLE news_tag ADD CONSTRAINT FK_BE3ED8A1BAD26311 FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE Comments ADD news_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE Comments ADD CONSTRAINT FK_A6E8F47CB5A459A0 FOREIGN KEY (news_id) REFERENCES news (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('CREATE INDEX IDX_A6E8F47CB5A459A0 ON Comments (news_id)');
        $this->addSql('ALTER TABLE Likes DROP FOREIGN KEY FK_880B617963379586');
        $this->addSql('ALTER TABLE Likes ADD CONSTRAINT FK_49CA4E7D63379586 FOREIGN KEY (comments_id) REFERENCES comments (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE news_tag DROP FOREIGN KEY FK_BE3ED8A1BAD26311');
        $this->addSql('ALTER TABLE news_tag ADD CONSTRAINT FK_BE3ED8A1BAD26311 FOREIGN KEY (tag_id) REFERENCES tag (id) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
