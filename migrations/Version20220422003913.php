<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220422003913 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE likes (id INT AUTO_INCREMENT NOT NULL, comments_id INT DEFAULT NULL, INDEX IDX_49CA4E7D63379586 (comments_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7D63379586 FOREIGN KEY (comments_id) REFERENCES Comments (id)');
        $this->addSql('ALTER TABLE comments DROP FOREIGN KEY FK_A6E8F47CB5A459A0');
        $this->addSql('ALTER TABLE comments ADD CONSTRAINT FK_A6E8F47CB5A459A0 FOREIGN KEY (news_id) REFERENCES News (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE likes');
        $this->addSql('ALTER TABLE Comments DROP FOREIGN KEY FK_A6E8F47CB5A459A0');
        $this->addSql('ALTER TABLE Comments ADD CONSTRAINT FK_A6E8F47CB5A459A0 FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE');
    }
}
