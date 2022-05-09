<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220422231243 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7D63379586');
        $this->addSql('DROP INDEX idx_49ca4e7d63379586 ON likes');
        $this->addSql('CREATE INDEX IDX_880B617963379586 ON likes (comments_id)');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7D63379586 FOREIGN KEY (comments_id) REFERENCES comments (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE Likes DROP FOREIGN KEY FK_880B617963379586');
        $this->addSql('DROP INDEX idx_880b617963379586 ON Likes');
        $this->addSql('CREATE INDEX IDX_49CA4E7D63379586 ON Likes (comments_id)');
        $this->addSql('ALTER TABLE Likes ADD CONSTRAINT FK_880B617963379586 FOREIGN KEY (comments_id) REFERENCES Comments (id)');
    }
}
