<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220506192313 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE comments ADD news_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE comments ADD CONSTRAINT FK_A6E8F47CB5A459A0 FOREIGN KEY (news_id) REFERENCES News (id)');
        $this->addSql('CREATE INDEX IDX_A6E8F47CB5A459A0 ON comments (news_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE Comments DROP FOREIGN KEY FK_A6E8F47CB5A459A0');
        $this->addSql('DROP INDEX IDX_A6E8F47CB5A459A0 ON Comments');
        $this->addSql('ALTER TABLE Comments DROP news_id');
    }
}
